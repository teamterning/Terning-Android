package com.terning.feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.terning.core.analytics.EventType
import com.terning.core.analytics.LocalTracker
import com.terning.core.designsystem.component.bottomsheet.SortingBottomSheet
import com.terning.core.designsystem.component.button.SortingButton
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.component.item.InternItemWithShadow
import com.terning.core.designsystem.extension.noRippleClickable
import com.terning.core.designsystem.extension.toast
import com.terning.core.designsystem.state.UiState
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.CalRed
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.designsystem.type.Grade
import com.terning.core.designsystem.type.WorkingPeriod
import com.terning.domain.home.entity.HomeFilteringInfo
import com.terning.domain.home.entity.HomeRecommendIntern
import com.terning.domain.home.entity.HomeUpcomingIntern
import com.terning.feature.dialog.cancel.ScrapCancelDialog
import com.terning.feature.dialog.detail.ScrapDialog
import com.terning.feature.home.component.HomeFilteringBottomSheet
import com.terning.feature.home.component.HomeFilteringScreen
import com.terning.feature.home.component.HomeRecommendEmptyIntern
import com.terning.feature.home.component.HomeUpcomingEmptyFilter
import com.terning.feature.home.component.HomeUpcomingEmptyIntern
import com.terning.feature.home.component.HomeUpcomingInternScreen

const val NAME_MAX_LENGTH = 5

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToCalendar: () -> Unit,
    navigateToIntern: (Long) -> Unit
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

    val amplitudeTracker = LocalTracker.current

    LaunchedEffect(key1 = true) {
        viewModel.getProfile()
        viewModel.getFilteringInfo()
    }

    LaunchedEffect(viewModel.homeSideEffect, lifecycleOwner) {
        viewModel.homeSideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is HomeSideEffect.ShowToast -> context.toast(sideEffect.message)
                    is HomeSideEffect.NavigateToCalendar -> navigateToCalendar()
                    is HomeSideEffect.NavigateToIntern -> navigateToIntern(sideEffect.announcementId)
                }
            }
    }

    HomeScreen(
        paddingValues = paddingValues,
        navigateToIntern = {
            amplitudeTracker.track(
                type = EventType.CLICK,
                name = "home_intern_card"
            )
            viewModel.navigateIntern(announcementId = it)
        },
        navigateToCalendar = {
            amplitudeTracker.track(
                type = EventType.CLICK,
                name = "check_schedule"
            )
            viewModel.navigateCalendar()
        },
        updateRecommendDialogVisibility = viewModel::updateRecommendDialogVisibility,
        getHomeUpcomingInternList = viewModel::getHomeUpcomingInternList,
        getRecommendInternsData = viewModel::getRecommendInternsData,
        viewModel = viewModel,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    navigateToIntern: (Long) -> Unit,
    navigateToCalendar: () -> Unit,
    updateRecommendDialogVisibility: (Boolean) -> Unit,
    getHomeUpcomingInternList: () -> Unit,
    getRecommendInternsData: (Int, Int?, Int?) -> Unit,
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

    var changeFilteringSheetState by remember { mutableStateOf(false) }

    val amplitudeTracker = LocalTracker.current

    if (homeState.sortingSheetVisibility) {
        SortingBottomSheet(
            onDismiss = {
                viewModel.updateSortingSheetVisibility(false)
            },
            currentSortBy = homeState.sortBy.ordinal,
            onSortChange = { sortBy ->
                amplitudeTracker.track(
                    type = EventType.CLICK,
                    name = when (sortBy) {
                        0 -> "filtered_deadline"
                        1 -> "filtered_short_term"
                        2 -> "filtered_long_term"
                        3 -> "filtered_scraps"
                        else -> "filtered_hits"
                    }
                )
                viewModel.updateSortBy(
                    sortBy,
                    homeFilteringInfo.startYear,
                    homeFilteringInfo.startMonth,
                )
            }
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
                amplitudeTracker.track(
                    type = EventType.CLICK,
                    name = "home_filtering_save"
                )
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

    if (homeState.homeRecommendDialogVisibility) {
        with(homeState.homeInternModel) {
            if (this != null) {
                if (isScrapped) {
                    ScrapCancelDialog(
                        internshipAnnouncementId = internshipAnnouncementId,
                        onDismissRequest = { isScrapCancelled ->
                            updateRecommendDialogVisibility(false)
                            if (isScrapCancelled) {
                                getHomeUpcomingInternList()
                                getRecommendInternsData(
                                    homeState.sortBy.ordinal,
                                    homeFilteringInfo.startYear,
                                    homeFilteringInfo.startMonth
                                )
                            }
                        }
                    )
                } else {
                    ScrapDialog(
                        title = title,
                        scrapColor = CalRed,
                        deadline = deadline,
                        startYearMonth = startYearMonth,
                        workingPeriod = workingPeriod,
                        internshipAnnouncementId = internshipAnnouncementId,
                        companyImage = companyImage,
                        isScrapped = isScrapped,
                        onDismissRequest = { isScrapped ->
                            updateRecommendDialogVisibility(false)
                            if (isScrapped) {
                                getRecommendInternsData(
                                    homeState.sortBy.ordinal,
                                    homeFilteringInfo.startYear,
                                    homeFilteringInfo.startMonth
                                )
                                getHomeUpcomingInternList()
                            }
                        },
                        onClickNavigateButton = navigateToIntern
                    )
                }
            }
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
                Column {
                    ShowUpcomingTitle()
                    ShowUpcomingIntern(
                        homeUpcomingInternState = homeState.homeUpcomingInternState,
                        navigateToIntern = navigateToIntern,
                        navigateToCalendar = navigateToCalendar,
                    )
                }
            }
            stickyHeader {
                Column(
                    modifier = Modifier
                        .background(White)
                ) {
                    ShowRecommendTitle(homeUserName)

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 15.dp, horizontal = 24.dp),
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
                        Spacer(modifier = Modifier.weight(1f))
                        SortingButton(
                            sortBy = homeState.sortBy.ordinal,
                            onCLick = { viewModel.updateSortingSheetVisibility(true) },
                        )
                        HomeFilteringScreen(
                            onChangeFilterClick = { changeFilteringSheetState = true },
                            modifier = Modifier
                                .padding(start = 8.dp)
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
                            amplitudeTracker.track(
                                type = EventType.CLICK,
                                name = "home_scrap"
                            )
                            updateRecommendDialogVisibility(true)
                            with(homeRecommendInternList[index]) {
                                viewModel.updateHomeInternModel(
                                    internshipAnnouncementId = internshipAnnouncementId,
                                    companyImage = companyImage,
                                    title = title,
                                    dDay = dDay,
                                    deadline = deadline,
                                    workingPeriod = workingPeriod,
                                    isScrapped = isScrapped,
                                    color = color,
                                    startYearMonth = startYearMonth,
                                )
                            }
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
    }
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
private fun ShowUpcomingTitle() {
    Text(
        text = stringResource(id = R.string.home_upcoming_title),
        modifier = Modifier
            .padding(
                top = 2.dp,
                bottom = 20.dp,
                start = 24.dp,
                end = 24.dp,
            ),
        style = TerningTheme.typography.title1,
        color = Black,
    )
}

@Composable
private fun ShowUpcomingIntern(
    homeUpcomingInternState: UiState<HomeUpcomingIntern>,
    navigateToIntern: (Long) -> Unit,
    navigateToCalendar: () -> Unit,
) {
    when (homeUpcomingInternState) {
        is UiState.Success -> {
            with(homeUpcomingInternState.data) {
                when {
                    !hasScrapped -> HomeUpcomingEmptyFilter()
                    hasScrapped && homeUpcomingInternDetail.isEmpty() -> HomeUpcomingEmptyIntern(
                        navigateToCalendar = navigateToCalendar
                    )

                    else -> HomeUpcomingInternScreen(
                        internList = homeUpcomingInternDetail,
                        navigateToIntern = navigateToIntern,
                    )
                }
            }
        }

        else -> HomeUpcomingEmptyFilter()
    }
}

@Composable
private fun ShowRecommendTitle(userName: String) {
    Text(
        text = stringResource(
            id = R.string.home_recommend_main_title,
            userName,
            if (userName.length > NAME_MAX_LENGTH) "\n" else " "
        ),
        style = TerningTheme.typography.title1,
        color = Black,
        modifier = Modifier
            .padding(
                top = 22.dp,
                start = 24.dp,
                end = 24.dp,
            ),
    )
}
