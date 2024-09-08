package com.terning.feature.home.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.terning.core.designsystem.component.bottomsheet.HomeFilteringBottomSheet
import com.terning.core.designsystem.component.bottomsheet.SortingBottomSheet
import com.terning.core.designsystem.component.button.SortingButton
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.component.item.InternItemWithShadow
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.noRippleClickable
import com.terning.core.extension.toast
import com.terning.core.state.UiState
import com.terning.core.type.Grade
import com.terning.core.type.WorkingPeriod
import com.terning.domain.entity.home.HomeFilteringInfo
import com.terning.domain.entity.home.HomeRecommendIntern
import com.terning.domain.entity.home.HomeUpcomingIntern
import com.terning.feature.R
import com.terning.feature.home.home.component.HomeFilteringScreen
import com.terning.feature.home.home.component.HomeRecommendEmptyIntern
import com.terning.feature.home.home.component.HomeTodayEmptyWithImg
import com.terning.feature.home.home.component.HomeTodayIntern
import com.terning.feature.home.home.model.HomeDialogState
import com.terning.feature.intern.navigation.navigateIntern

const val NAME_START_LENGTH = 7
const val NAME_END_LENGTH = 12

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = White
        )
        systemUiController.setNavigationBarColor(
            color = White
        )
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val homeDialogState by viewModel.homeDialogState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.getFilteringInfo()
    }

    LaunchedEffect(viewModel.homeSideEffect, lifecycleOwner) {
        viewModel.homeSideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is HomeSideEffect.ShowToast -> context.toast(sideEffect.message)
                    is HomeSideEffect.NavigateToHome -> navController.navigateUp()
                }
            }
    }

    HomeScreen(
        paddingValues = paddingValues,
        homeDialogState = homeDialogState,
        navigateToIntern = { navController.navigateIntern(announcementId = it) },
        viewModel = viewModel,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    homeDialogState: HomeDialogState,
    navigateToIntern: (Long) -> Unit,
    viewModel: HomeViewModel,
) {
    val homeState by viewModel.homeState.collectAsStateWithLifecycle()

    val homeUserName = when (homeState.homeUserNameState) {
        is UiState.Success -> (homeState.homeUserNameState as UiState.Success<String>).data
        else -> ""
    }

    val homeFilteringInfo = when (homeState.homeFilteringInfoState) {
        is UiState.Success -> (homeState.homeFilteringInfoState as UiState.Success<HomeFilteringInfo>).data
        else -> HomeFilteringInfo(null, null, null, null)
    }

    val homeRecommendInternList = when (homeState.homeRecommendInternState) {
        is UiState.Success -> (homeState.homeRecommendInternState as UiState.Success<HomeRecommendIntern>).data.homeRecommendInternDetail
        else -> listOf()
    }

    val homeRecommendInternTotal = when (homeState.homeRecommendInternState) {
        is UiState.Success -> (homeState.homeRecommendInternState as UiState.Success<HomeRecommendIntern>).data.totalCount
        else -> 0
    }

    val currentSortBy: MutableState<Int> = remember { mutableIntStateOf(0) }
    var sortingSheetState by remember { mutableStateOf(false) }
    var changeFilteringSheetState by remember { mutableStateOf(false) }
    var scrapId by remember { mutableIntStateOf(-1) }

    if (sortingSheetState) {
        SortingBottomSheet(
            onDismiss = {
                sortingSheetState = false
                viewModel.getRecommendInternsData(
                    currentSortBy.value,
                    homeFilteringInfo.startYear,
                    homeFilteringInfo.startMonth,
                )
            },
            currentSortBy = currentSortBy.value,
            newSortBy = currentSortBy
        )
    }

    if (changeFilteringSheetState) {
        HomeFilteringBottomSheet(
            defaultGrade = homeFilteringInfo.grade?.let { Grade.fromString(it) },
            defaultWorkingPeriod = homeFilteringInfo.workingPeriod?.let {
                WorkingPeriod.fromString(it)
            },
            defaultStartYear = homeFilteringInfo.startYear,
            defaultStartMonth = homeFilteringInfo.startMonth,
            onDismiss = { changeFilteringSheetState = false },
            onChangeButtonClick = { grade, workingPeriod, year, month ->
                viewModel.putFilteringInfo(grade, workingPeriod, year, month)
                changeFilteringSheetState = false
            }
        )
    }

    LaunchedEffect(changeFilteringSheetState) {
        if (!changeFilteringSheetState) {
            viewModel.getFilteringInfo()
        }
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .background(White)
            .padding(paddingValues)
    ) {
        TerningImage(
            painter = R.drawable.ic_terning_logo_typo,
            modifier = Modifier
                .padding(start = 24.dp, top = 16.dp, bottom = 16.dp)
        )

        LazyColumn(
            contentPadding = PaddingValues(
                top = 2.dp,
                bottom = 20.dp,
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            item {
                Column(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                ) {
                    ShowMainTitleWithName(homeUserName)
                    ShowUpcomingIntern(
                        homeUpcomingInternState = homeState.homeUpcomingInternState,
                        homeDialogState = homeDialogState,
                        navigateToIntern = { navigateToIntern(it) }
                    )
                }
            }
            stickyHeader {
                Column(
                    modifier = Modifier
                        .background(White)
                ) {
                    ShowRecommendTitle()
                    ShowInternFilter(
                        homeFilteringInfo = homeFilteringInfo,
                        onChangeFilterClick = { changeFilteringSheetState = true },
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 18.dp),
                    ) {
                        Row {
                            Text(
                                text = stringResource(id = R.string.home_recommend_total),
                                style = TerningTheme.typography.detail1,
                                color = Grey400,
                                modifier = Modifier
                                    .padding(end = 3.dp),
                            )
                            Text(
                                text = homeRecommendInternTotal.toString(),
                                style = TerningTheme.typography.button3,
                                color = TerningMain,
                            )
                            Text(
                                text = stringResource(id = R.string.home_recommend_count),
                                style = TerningTheme.typography.detail1,
                                color = Grey400,
                            )
                        }
                        Row {
                            SortingButton(
                                sortBy = currentSortBy.value,
                                onCLick = { sortingSheetState = true },
                                modifier = Modifier
                                    .padding(vertical = 4.dp)
                            )
                        }
                    }
                }
            }

            if (homeRecommendInternList.isNotEmpty()) {
                items(homeRecommendInternList.size) { index ->
                    RecommendInternItem(
                        navigateToIntern = navigateToIntern,
                        intern = homeRecommendInternList[index],
                        onScrapButtonClicked = {
                            viewModel.updateScrapDialogVisible(true)
                            viewModel.updateIsToday(false)
                            scrapId = index
                        }
                    )
                }
            } else {
                item {
                    HomeRecommendEmptyIntern(
                        text =
                        if (homeState.homeFilteringInfoState is UiState.Success && homeFilteringInfo.grade == null) R.string.home_recommend_no_filtering
                        else R.string.home_recommend_no_intern
                    )
                }
            }
        }
//        if (homeState.homeFilteringInfoState is UiState.Success && homeFilteringInfo.grade == null) {
//            HomeFilteringEmptyIntern(
//                modifier = Modifier
//                    .padding(horizontal = 24.dp)
//                    .fillMaxSize()
//            )
//        } else if (homeRecommendInternList.isEmpty()) {
//            HomeRecommendEmptyIntern()
//        }
    }
//
//    if (homeDialogState.isScrapDialogVisible && !homeDialogState.isToday) {
//        TerningBasicDialog(
//            onDismissRequest = {
//                viewModel.updateScrapDialogVisible(false)
//                viewModel.updatePaletteOpen(false)
//            },
//            content = {
//                if (homeRecommendInternList[scrapId].scrapId != null) {
//                    ScrapCancelDialogContent(
//                        onClickScrapCancel = {
//                            viewModel.updateScrapDialogVisible(false)
//                            viewModel.deleteScrap(
//                                homeRecommendInternList[scrapId].scrapId ?: -1,
//                            )
//                            if (homeDialogState.isScrappedState) {
//                                viewModel.getRecommendInternsData(
//                                    currentSortBy.value,
//                                    homeFilteringInfo.startYear,
//                                    homeFilteringInfo.startMonth,
//                                )
//                                viewModel.updateScrapped(false)
//                            }
//                        }
//                    )
//                } else {
//                    val colorList = listOf(
//                        CalRed,
//                        CalOrange1,
//                        CalOrange2,
//                        CalYellow,
//                        CalGreen1,
//                        CalGreen2,
//                        CalBlue1,
//                        CalBlue2,
//                        CalPurple,
//                        CalPink,
//                    )
//
//                    val selectedColorIndex =
//                        colorList.indexOf(homeDialogState.selectedColor).takeIf { it >= 0 } ?: 0
//
//                    with(homeRecommendInternList[scrapId]) {
//                        HomeRecommendInternDialog(
//                            internInfoList = listOf(
//                                stringResource(id = R.string.intern_info_d_day) to deadline,
//                                stringResource(id = R.string.intern_info_working) to workingPeriod,
//                                stringResource(id = R.string.intern_info_start_date) to startYearMonth,
//                            ),
//                            clickAction = {
//                                if (homeDialogState.isPaletteOpen) {
//                                    viewModel.updatePaletteOpen(false)
//                                    viewModel.updateColorChange(false)
//                                    viewModel.updateScrapDialogVisible(false)
//                                } else {
//                                    if (homeDialogState.isColorChange) {
//                                        viewModel.updateColorChange(false)
//                                    }
//                                    viewModel.updateScrapDialogVisible(false)
//                                }
//                                viewModel.postScrap(
//                                    homeRecommendInternList[scrapId].internshipAnnouncementId,
//                                    selectedColorIndex,
//                                )
//                                viewModel.updateScrapDialogVisible(false)
//                                if (homeDialogState.isScrappedState) {
//                                    viewModel.getRecommendInternsData(
//                                        currentSortBy.value,
//                                        homeFilteringInfo.startYear,
//                                        homeFilteringInfo.startMonth,
//                                    )
//                                    viewModel.updateScrapped(false)
//                                }
//                            },
//                            homeRecommendIntern = this,
//                        )
//                    }
//                }
//            }
//        )
//    }
}


@Composable
private fun RecommendInternItem(
    intern: HomeRecommendIntern.HomeRecommendInternDetail,
    navigateToIntern: (Long) -> Unit,
    onScrapButtonClicked: (Long) -> Unit,
) {
    InternItemWithShadow(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .noRippleClickable {
                navigateToIntern(intern.internshipAnnouncementId)
            },
        imageUrl = intern.companyImage,
        title = intern.title,
        dateDeadline = intern.dDay,
        workingPeriod = intern.workingPeriod,
        isScrapped = intern.isScrapped,
        shadowRadius = 5.dp,
        shadowWidth = 1.dp,
        onScrapButtonClicked = onScrapButtonClicked,
    )
}

@Composable
private fun ShowMainTitleWithName(userName: String) {
    Text(
        text = stringResource(
            id = R.string.home_upcoming_title,
            if (userName.length in NAME_START_LENGTH..NAME_END_LENGTH) "\n" + userName
            else userName
        ),
        modifier = Modifier
            .padding(top = 11.dp, bottom = 19.dp)
            .padding(horizontal = 24.dp),
        style = TerningTheme.typography.title1,
        color = Black,
    )
}

@Composable
private fun ShowUpcomingIntern(
    homeUpcomingInternState: UiState<List<HomeUpcomingIntern>>,
    homeDialogState: HomeDialogState,
    navigateToIntern: (Long) -> Unit,
) {
    when (homeUpcomingInternState) {
        is UiState.Success -> {
            if (homeUpcomingInternState.data.isEmpty()) {
                HomeTodayEmptyWithImg()
            } else {
                HomeTodayIntern(
                    internList = homeUpcomingInternState.data,
                    homeDialogState = homeDialogState,
                    navigateToIntern = navigateToIntern
                )
            }
        }

        is UiState.Loading -> HomeTodayEmptyWithImg()
        else -> {}
    }
}

@Composable
private fun ShowRecommendTitle() {
    Text(
        text = stringResource(id = R.string.home_recommend_main_title),
        style = TerningTheme.typography.title1,
        color = Black,
        modifier = Modifier
            .padding(top = 5.dp)
            .padding(horizontal = 24.dp),
    )
}

@Composable
private fun ShowInternFilter(
    homeFilteringInfo: HomeFilteringInfo,
    onChangeFilterClick: () -> Unit,
) {
    HomeFilteringScreen(
        homeFilteringInfo = homeFilteringInfo,
        onChangeFilterClick = onChangeFilterClick,
    )
}
