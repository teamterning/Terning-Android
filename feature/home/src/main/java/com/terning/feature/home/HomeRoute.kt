package com.terning.feature.home

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.terning.core.analytics.EventType
import com.terning.core.analytics.LocalTracker
import com.terning.core.designsystem.R.raw.paging_loading_animation
import com.terning.core.designsystem.component.bottomsheet.SortingBottomSheet
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.component.item.InternItemWithShadow
import com.terning.core.designsystem.component.item.TerningLottieAnimation
import com.terning.core.designsystem.extension.noRippleClickable
import com.terning.core.designsystem.extension.toast
import com.terning.core.designsystem.state.UiState
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.CalRed
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.designsystem.type.JobType
import com.terning.domain.home.entity.HomeFilteringInfo
import com.terning.domain.home.entity.HomeRecommendedIntern
import com.terning.domain.home.entity.HomeUpcomingIntern
import com.terning.feature.dialog.cancel.ScrapCancelDialog
import com.terning.feature.dialog.detail.ScrapDialog
import com.terning.feature.home.component.HomeFilteringScreen
import com.terning.feature.home.component.HomeRecommendEmptyIntern
import com.terning.feature.home.component.HomeSortingButton
import com.terning.feature.home.component.HomeUpcomingEmptyFilter
import com.terning.feature.home.component.HomeUpcomingEmptyIntern
import com.terning.feature.home.component.HomeUpcomingInternScreen
import com.terning.feature.home.component.bottomsheet.HomeFilteringBottomSheet
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

const val NAME_MAX_LENGTH = 5
private const val ZERO_TOTAL_COUNT = 0

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToCalendar: () -> Unit,
    navigateToIntern: (Long) -> Unit
) {
    val permissionState =
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    val homeState by viewModel.homeState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (!permissionState.status.isGranted) {
            permissionState.launchPermissionRequest()

            snapshotFlow { permissionState.status }
                .map { it is PermissionStatus.Granted }
                .distinctUntilChanged()
                .collectLatest { isGranted ->
                    viewModel.updateAlarmAvailability(isGranted)
                    viewModel.completeNotificationCheck()
                }
        }
    }

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
        viewModel.getHomeUpcomingInternList()
        viewModel.getRecommendInternFlow()
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
        updateSortingSheetVisibility = viewModel::updateSortingSheetVisibility,
        updateSortBy = viewModel::updateSortBy,
        getHomeUpcomingInternList = viewModel::getHomeUpcomingInternList,
        updateInternModelScrapState = viewModel::updateInternScrapState,
        viewModel = viewModel,
        homeState = homeState
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    navigateToIntern: (Long) -> Unit,
    navigateToCalendar: () -> Unit,
    updateRecommendDialogVisibility: (Boolean) -> Unit,
    updateSortingSheetVisibility: (Boolean) -> Unit,
    updateSortBy: (Int) -> Unit,
    getHomeUpcomingInternList: () -> Unit,
    updateInternModelScrapState: () -> Unit,
    viewModel: HomeViewModel,
    homeState: HomeState
) {
    val recommendedInternList = viewModel.recommendInternFlow.collectAsLazyPagingItems()

    val homeUserName = when (homeState.homeUserNameState) {
        is UiState.Success -> (homeState.homeUserNameState as UiState.Success<String>).data
        else -> ""
    }

    val homeFilteringInfo = when (homeState.homeFilteringInfoState) {
        is UiState.Success -> (homeState.homeFilteringInfoState as UiState.Success<HomeFilteringInfo>).data
        else -> HomeFilteringInfo(
            grade = null,
            workingPeriod = null,
            startYear = null,
            startMonth = null,
            jobType = JobType.TOTAL.stringValue
        )
    }

    val homeRecommendInternTotal = remember(recommendedInternList.loadState.refresh) {
        if (recommendedInternList.itemCount > 0) {
            recommendedInternList[0]?.totalCount ?: ZERO_TOTAL_COUNT
        } else {
            ZERO_TOTAL_COUNT
        }
    }

    var changeFilteringSheetState by remember { mutableStateOf(false) }

    val amplitudeTracker = LocalTracker.current

    if (homeState.sortingSheetVisibility) {
        SortingBottomSheet(
            onDismiss = {
                updateSortingSheetVisibility(false)
            },
            currentSortBy = homeState.sortBy.ordinal,
            onSortChange = { sortBy ->
                amplitudeTracker.track(
                    type = EventType.CLICK,
                    name = when (sortBy) {
                        0 -> "in_order_of_deadline"
                        1 -> "in_order_of_short_term"
                        2 -> "in_order_of_long_term"
                        3 -> "in_order_of_scraps"
                        else -> "in_order_of_hits"
                    }
                )
                updateSortBy(sortBy)
            }
        )
    }

    if (changeFilteringSheetState) {
        HomeFilteringBottomSheet(
            onDismiss = { changeFilteringSheetState = false },
            defaultFilteringInfo = homeFilteringInfo,
            onChangeButtonClick = { grade, workingPeriod, year, month, jobType ->
                viewModel.putFilteringInfo(grade, workingPeriod, year, month, jobType)
                changeFilteringSheetState = false
            }
        )
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
                                updateInternModelScrapState()
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
                                updateInternModelScrapState()
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
            contentPadding = PaddingValues(bottom = 20.dp),
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
                            .padding(horizontal = 24.dp)
                            .padding(top = 15.dp, bottom = 3.dp),
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
                        HomeSortingButton(
                            sortBy = homeState.sortBy.ordinal,
                            onCLick = { updateSortingSheetVisibility(true) },
                        )
                        HomeFilteringScreen(
                            onChangeFilterClick = { changeFilteringSheetState = true },
                            modifier = Modifier
                                .padding(start = 8.dp)
                        )
                    }
                }
            }

            recommendedInternLazyList(
                recommendedInternList = recommendedInternList,
                navigateToIntern = navigateToIntern,
                onScrapButtonClicked = { internItem ->
                    with(internItem) {
                        amplitudeTracker.track(
                            type = EventType.CLICK,
                            name = "home_scrap"
                        )
                        updateRecommendDialogVisibility(true)
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
    }
}

private fun LazyListScope.recommendedInternLazyList(
    recommendedInternList: LazyPagingItems<HomeRecommendedIntern>,
    navigateToIntern: (Long) -> Unit,
    onScrapButtonClicked: (HomeRecommendedIntern) -> Unit,
) {
    if (recommendedInternList.itemCount == 0) {
        item {
            HomeRecommendEmptyIntern(
                text = R.string.home_recommend_no_intern,
            )
        }
    } else {
        items(recommendedInternList.itemCount, key = { it }) { index ->
            recommendedInternList[index]?.run {
                RecommendInternItem(
                    navigateToIntern = navigateToIntern,
                    intern = this,
                    onScrapButtonClicked = { onScrapButtonClicked(this) },
                )
            }
        }

        item {
            if (recommendedInternList.loadState.append == LoadState.Loading) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    TerningLottieAnimation(
                        jsonFile = paging_loading_animation,
                        modifier = Modifier.size(28.dp),
                        iterations = Int.MAX_VALUE,
                    )
                }
            }
        }
    }
}

@Composable
private fun RecommendInternItem(
    intern: HomeRecommendedIntern,
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
        isApplyClosed = (intern.dDay == stringResource(id = R.string.intern_apply_closed)),
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
                        internList = homeUpcomingInternDetail.toImmutableList(),
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
