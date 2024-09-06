package com.terning.feature.home.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
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
import com.terning.core.designsystem.component.bottomsheet.SortingBottomSheet
import com.terning.core.designsystem.component.button.SortingButton
import com.terning.core.designsystem.component.dialog.ScrapCancelDialogContent
import com.terning.core.designsystem.component.dialog.TerningBasicDialog
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.component.item.InternItemWithShadow
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.CalBlue1
import com.terning.core.designsystem.theme.CalBlue2
import com.terning.core.designsystem.theme.CalGreen1
import com.terning.core.designsystem.theme.CalGreen2
import com.terning.core.designsystem.theme.CalOrange1
import com.terning.core.designsystem.theme.CalOrange2
import com.terning.core.designsystem.theme.CalPink
import com.terning.core.designsystem.theme.CalPurple
import com.terning.core.designsystem.theme.CalRed
import com.terning.core.designsystem.theme.CalYellow
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.noRippleClickable
import com.terning.core.extension.toast
import com.terning.core.state.UiState
import com.terning.domain.entity.home.HomeFilteringInfo
import com.terning.domain.entity.home.HomeRecommendIntern
import com.terning.domain.entity.home.HomeTodayIntern
import com.terning.feature.R
import com.terning.feature.home.changefilter.navigation.navigateChangeFilter
import com.terning.feature.home.home.component.HomeFilteringEmptyIntern
import com.terning.feature.home.home.component.HomeFilteringScreen
import com.terning.feature.home.home.component.HomeRecommendEmptyIntern
import com.terning.feature.home.home.component.HomeRecommendInternDialog
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
                    is HomeSideEffect.NavigateToChangeFilter -> navController.navigateChangeFilter()
                    is HomeSideEffect.NavigateToHome -> navController.navigateUp()
                }
            }
    }

    HomeScreen(
        paddingValues = paddingValues,
        homeDialogState = homeDialogState,
        onChangeFilterClick = { navController.navigateChangeFilter() },
        navigateToIntern = { navController.navigateIntern(announcementId = it) },
        viewModel = viewModel,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    homeDialogState: HomeDialogState,
    onChangeFilterClick: () -> Unit,
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
        is UiState.Success -> (homeState.homeRecommendInternState as UiState.Success<List<HomeRecommendIntern>>).data
        else -> listOf()
    }

    val currentSortBy: MutableState<Int> = remember { mutableIntStateOf(0) }
    var sheetState by remember { mutableStateOf(false) }
    var scrapId by remember { mutableIntStateOf(-1) }

    if (sheetState) {
        SortingBottomSheet(
            onDismiss = {
                sheetState = false
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
                    ShowTodayIntern(
                        homeTodayInternState = homeState.homeTodayInternState,
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
                    ShowInternFilter(homeFilteringInfo = homeFilteringInfo, onChangeFilterClick)

                    HorizontalDivider(
                        thickness = 4.dp,
                        color = Grey150,
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        SortingButton(
                            sortBy = currentSortBy.value,
                            onCLick = { sheetState = true },
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                        )
                        Spacer(
                            modifier = Modifier.padding(9.dp)
                        )
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
            }
        }
        if (homeState.homeFilteringInfoState is UiState.Success && homeFilteringInfo.grade == null) {
            HomeFilteringEmptyIntern(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxSize()
            )
        } else if (homeRecommendInternList.isEmpty()) {
            HomeRecommendEmptyIntern()
        }
    }

    if (homeDialogState.isScrapDialogVisible && !homeDialogState.isToday) {
        TerningBasicDialog(
            onDismissRequest = {
                viewModel.updateScrapDialogVisible(false)
                viewModel.updatePaletteOpen(false)
            },
            content = {
                if (homeRecommendInternList[scrapId].scrapId != null) {
                    ScrapCancelDialogContent(
                        onClickScrapCancel = {
                            viewModel.updateScrapDialogVisible(false)
                            viewModel.deleteScrap(
                                homeRecommendInternList[scrapId].scrapId ?: -1,
                            )
                            if (homeDialogState.isScrappedState) {
                                viewModel.getRecommendInternsData(
                                    currentSortBy.value,
                                    homeFilteringInfo.startYear,
                                    homeFilteringInfo.startMonth,
                                )
                                viewModel.updateScrapped(false)
                            }
                        }
                    )
                } else {
                    val colorList = listOf(
                        CalRed,
                        CalOrange1,
                        CalOrange2,
                        CalYellow,
                        CalGreen1,
                        CalGreen2,
                        CalBlue1,
                        CalBlue2,
                        CalPurple,
                        CalPink,
                    )

                    val selectedColorIndex =
                        colorList.indexOf(homeDialogState.selectedColor).takeIf { it >= 0 } ?: 0

                    with(homeRecommendInternList[scrapId]) {
                        HomeRecommendInternDialog(
                            internInfoList = listOf(
                                stringResource(id = R.string.intern_info_d_day) to deadline,
                                stringResource(id = R.string.intern_info_working) to workingPeriod,
                                stringResource(id = R.string.intern_info_start_date) to startYearMonth,
                            ),
                            clickAction = {
                                if (homeDialogState.isPaletteOpen) {
                                    viewModel.updatePaletteOpen(false)
                                    viewModel.updateColorChange(false)
                                    viewModel.updateScrapDialogVisible(false)
                                } else {
                                    if (homeDialogState.isColorChange) {
                                        viewModel.updateColorChange(false)
                                    }
                                    viewModel.updateScrapDialogVisible(false)
                                }
                                viewModel.postScrap(
                                    homeRecommendInternList[scrapId].internshipAnnouncementId,
                                    selectedColorIndex,
                                )
                                viewModel.updateScrapDialogVisible(false)
                                if (homeDialogState.isScrappedState) {
                                    viewModel.getRecommendInternsData(
                                        currentSortBy.value,
                                        homeFilteringInfo.startYear,
                                        homeFilteringInfo.startMonth,
                                    )
                                    viewModel.updateScrapped(false)
                                }
                            },
                            homeRecommendIntern = this,
                        )
                    }
                }
            }
        )
    }
}


@Composable
private fun RecommendInternItem(
    intern: HomeRecommendIntern,
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
            id = R.string.home_today_title,
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
private fun ShowTodayIntern(
    homeTodayInternState: UiState<List<HomeTodayIntern>>,
    homeDialogState: HomeDialogState,
    navigateToIntern: (Long) -> Unit,
) {
    when (homeTodayInternState) {
        is UiState.Success -> {
            if (homeTodayInternState.data.isEmpty()) {
                HomeTodayEmptyWithImg()
            } else {
                HomeTodayIntern(
                    internList = homeTodayInternState.data,
                    homeDialogState = homeDialogState,
                    navigateToIntern = { navigateToIntern(it) },
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
        text = stringResource(id = R.string.home_recommend_sub_title),
        style = TerningTheme.typography.detail2,
        color = Black,
        modifier = Modifier
            .padding(top = 9.dp)
            .padding(horizontal = 24.dp),
    )

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
    if (homeFilteringInfo.grade == null) {
        HomeFilteringScreen(
            grade = stringResource(id = R.string.home_recommend_no_filtering_hyphen),
            period = stringResource(id = R.string.home_recommend_no_filtering_hyphen),
            startYear = stringResource(id = R.string.home_recommend_no_filtering_hyphen),
            onChangeFilterClick = onChangeFilterClick,
        )
    } else {
        with(homeFilteringInfo) {
            HomeFilteringScreen(
                grade = (grade?.plus(1)).toString() + stringResource(id = R.string.home_recommend_filtering_grade),
                period = stringResource(
                    id = when (workingPeriod) {
                        0 -> R.string.filtering_status2_button1
                        1 -> R.string.filtering_status2_button2
                        2 -> R.string.filtering_status2_button3
                        else -> R.string.home_recommend_no_filtering_hyphen
                    }
                ),
                startYear = startYear.toString() + stringResource(id = R.string.home_recommend_filtering_startYear)
                        + "  " + startMonth.toString() + stringResource(id = R.string.home_recommend_filtering_startMonth),
                onChangeFilterClick = onChangeFilterClick,
            )
        }
    }
}
