package com.terning.feature.calendar.calendar

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.terning.core.analytics.EventType
import com.terning.core.analytics.LocalTracker
import com.terning.core.designsystem.component.topappbar.CalendarTopAppBar
import com.terning.core.designsystem.extension.getWeekIndexContainingSelectedDate
import com.terning.core.designsystem.theme.Grey200
import com.terning.feature.calendar.calendar.component.ScreenTransition
import com.terning.feature.calendar.calendar.component.WeekDaysHeader
import com.terning.feature.calendar.calendar.model.CalendarUiState
import com.terning.feature.calendar.calendar.model.DayModel
import com.terning.feature.calendar.calendar.model.TerningCalendarModel
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
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val amplitudeTracker = LocalTracker.current

    CalendarScreen(
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
        },
        modifier = modifier,
    )
}

@Composable
private fun CalendarScreen(
    uiState: CalendarUiState,
    navigateToAnnouncement: (Long) -> Unit,
    onClickNewDate: (DayModel) -> Unit,
    updateSelectedDate: (DayModel) -> Unit,
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
                val date = uiState.calendarModel.getLocalDateByPage(current)
                val month = uiState.calendarModel.getMonthModelByPage(current)

                val newDate = LocalDate.of(
                    date.year,
                    date.month,
                    uiState.selectedDate.date.dayOfMonth.coerceAtMost(date.month.minLength())
                )

                val currentWeek = newDate.getWeekIndexContainingSelectedDate(month.inDays)
                updateSelectedDate(DayModel(newDate, currentWeek))
            }
    }


    Column(
        modifier = modifier,
    ) {
        CalendarTopAppBar(
            date = uiState.calendarModel.getYearMonthByPage(pagerState.settledPage),
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

        CalendarListTransition(
            isCalendarEnabled = !uiState.isListEnabled,
            calendarModel = uiState.calendarModel,
            pagerState = pagerState,
            onNavigateToAnnouncement = navigateToAnnouncement,
            onNavigateUpToCalendar = disableListVisibility,
            calendarContent = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    WeekDaysHeader()

                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Grey200
                    )

                    MonthWeekTransition(
                        isMonthEnabled = !uiState.isWeekEnabled,
                        selectedDate = uiState.selectedDate,
                        calendarModel = uiState.calendarModel,
                        pagerState = pagerState,
                        onSelectDate = { newDate -> onClickNewDate(newDate) },
                        onNavigateToAnnouncement = navigateToAnnouncement,
                        onNavigateUpToMonth = disableWeekVisibility
                    )
                }
            }
        )
    }
}


/** 달력 <-> 목록 전환 컴포저블 */
@Composable
private fun CalendarListTransition(
    isCalendarEnabled: Boolean,
    calendarModel: TerningCalendarModel,
    pagerState: PagerState,
    onNavigateToAnnouncement: (Long) -> Unit,
    onNavigateUpToCalendar: () -> Unit,
    calendarContent: @Composable () -> Unit,
) {
    ScreenTransition(
        targetState = isCalendarEnabled,
        transitionOne = slideInHorizontally { fullWidth -> -fullWidth } togetherWith
                slideOutHorizontally { fullWidth -> fullWidth },
        transitionTwo = slideInHorizontally { fullWidth -> fullWidth } togetherWith
                slideOutHorizontally { fullWidth -> -fullWidth },
        contentOne = {
            calendarContent()
        },
        contentTwo = {
            CalendarListRoute(
                calendarModel = calendarModel,
                navigateToAnnouncement = onNavigateToAnnouncement,
                navigateUp = onNavigateUpToCalendar,
                pagerState = pagerState,
                modifier = Modifier
                    .fillMaxSize()
            )
        },
    )
}

/**월간 <-> 주간 전환 컴포저블*/
@Composable
private fun MonthWeekTransition(
    isMonthEnabled: Boolean,
    selectedDate: DayModel,
    calendarModel: TerningCalendarModel,
    pagerState: PagerState,
    onSelectDate: (DayModel) -> Unit,
    onNavigateToAnnouncement: (Long) -> Unit,
    onNavigateUpToMonth: () -> Unit,
) {
    ScreenTransition(
        targetState = isMonthEnabled,
        transitionOne = slideInVertically { fullHeight -> -fullHeight } togetherWith
                slideOutVertically { fullHeight -> fullHeight },
        transitionTwo = slideInVertically { fullHeight -> fullHeight } togetherWith
                slideOutVertically { fullHeight -> -fullHeight },
        contentOne = {
            CalendarMonthRoute(
                selectedDate = selectedDate,
                updateSelectedDate = { newDate ->
                    if (!pagerState.isScrollInProgress)
                        onSelectDate(newDate)
                },
                pagerState = pagerState,
                calendarModel = calendarModel
            )
        },
        contentTwo = {
            CalendarWeekRoute(
                modifier = Modifier
                    .fillMaxSize(),
                navigateUp = onNavigateUpToMonth,
                navigateToAnnouncement = onNavigateToAnnouncement,
                updateSelectedDate = onSelectDate,
                selectedDate = selectedDate,
                calendarModel = calendarModel,
                pagerState = pagerState,
            )
        }
    )
}


