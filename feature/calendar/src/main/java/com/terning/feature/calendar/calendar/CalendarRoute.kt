package com.terning.feature.calendar.calendar

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.terning.core.analytics.EventType
import com.terning.core.analytics.LocalTracker
import com.terning.core.designsystem.component.topappbar.CalendarTopAppBar
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.White
import com.terning.feature.calendar.calendar.component.ScreenTransition
import com.terning.feature.calendar.calendar.component.WeekDaysHeader
import com.terning.feature.calendar.calendar.model.CalendarModel.Companion.getLocalDateByPage
import com.terning.feature.calendar.calendar.model.CalendarModel.Companion.getYearMonthByPage
import com.terning.feature.calendar.calendar.model.CalendarUiState
import com.terning.feature.calendar.calendar.model.LocalPagerState
import com.terning.feature.calendar.list.CalendarListRoute
import com.terning.feature.calendar.month.CalendarMonthRoute
import com.terning.feature.calendar.week.CalendarWeekRoute
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun CalendarRoute(
    navigateToAnnouncement: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(lifecycleOwner = lifecycleOwner)
    val amplitudeTracker = LocalTracker.current

    CalendarScreen(
        modifier = modifier,
        uiState = uiState,
        navigateToAnnouncement = navigateToAnnouncement,
        onClickNewDate = viewModel::onSelectNewDate,
        updateSelectedDate = viewModel::updateSelectedDate,
        disableListVisibility = { viewModel.updateListVisibility(false) },
        disableWeekVisibility = { viewModel.updateWeekVisibility(false) },
        onClickListButton = {
            if (!uiState.isListEnabled) {
                amplitudeTracker.track(
                    type = EventType.CLICK,
                    name = "calendar_list"
                )
            }
            viewModel.updateListVisibility(!uiState.isListEnabled)
        }
    )
}

@Composable
private fun CalendarScreen(
    uiState: CalendarUiState,
    navigateToAnnouncement: (Long) -> Unit,
    onClickNewDate: (LocalDate) -> Unit,
    updateSelectedDate: (LocalDate) -> Unit,
    disableListVisibility: () -> Unit,
    disableWeekVisibility: () -> Unit,
    onClickListButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()

    val pagerState = rememberPagerState(
        initialPage = uiState.calendarModel.initialPage,
        pageCount = { uiState.calendarModel.pageCount }
    )

    LaunchedEffect(key1 = pagerState, key2 = uiState.selectedDate) {
        snapshotFlow { pagerState.currentPage }
            .collect { current ->
                val date = getLocalDateByPage(current)

                val newDate = LocalDate.of(
                    date.year,
                    date.month,
                    uiState.selectedDate.dayOfMonth.coerceAtMost(date.month.minLength())
                )
                updateSelectedDate(newDate)
            }
    }

    CompositionLocalProvider(
        LocalPagerState provides pagerState
    ) {
        Column(
            modifier = modifier,
        ) {
            CalendarTopAppBar(
                date = getYearMonthByPage(pagerState.settledPage),
                isListExpanded = uiState.isListEnabled,
                onListButtonClicked = onClickListButton,
                onMonthNavigationButtonClicked = { direction ->
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(
                            page = pagerState.settledPage + direction,
                            animationSpec = tween(500)
                        )
                    }
                }
            )
            ScreenTransition(
                targetState = !uiState.isListEnabled,
                transitionOne = slideInHorizontally { fullWidth -> -fullWidth } togetherWith
                        slideOutHorizontally { fullWidth -> fullWidth },
                transitionTwo = slideInHorizontally { fullWidth -> fullWidth } togetherWith
                        slideOutHorizontally { fullWidth -> -fullWidth },
                contentOne = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        WeekDaysHeader()

                        HorizontalDivider(
                            thickness = 1.dp,
                            color = Grey200
                        )

                        ScreenTransition(
                            targetState = !uiState.isWeekEnabled,
                            transitionOne = slideInVertically { fullHeight -> -fullHeight } togetherWith
                                    slideOutVertically { fullHeight -> fullHeight },
                            transitionTwo = slideInVertically { fullHeight -> fullHeight } togetherWith
                                    slideOutVertically { fullHeight -> -fullHeight },
                            contentOne = {
                                CalendarMonthRoute(
                                    selectedDate = uiState.selectedDate,
                                    updateSelectedDate = { newDate ->
                                        if (!pagerState.isScrollInProgress)
                                            onClickNewDate(newDate)
                                    },
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(White),
                                )
                            },
                            contentTwo = {
                                CalendarWeekRoute(
                                    calendarUiState = uiState,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    navigateUp = disableWeekVisibility,
                                    navigateToAnnouncement = navigateToAnnouncement,
                                    updateSelectedDate = onClickNewDate
                                )
                            }
                        )
                    }
                },
                contentTwo = {
                    CalendarListRoute(
                        navigateToAnnouncement = navigateToAnnouncement,
                        navigateUp = disableListVisibility,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                },
            )
        }
    }
}

