package com.terning.feature.calendar.calendar

import androidx.activity.compose.BackHandler
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.terning.core.designsystem.component.topappbar.CalendarTopAppBar
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.White
import com.terning.feature.calendar.calendar.component.ScreenTransition
import com.terning.feature.calendar.calendar.component.WeekDaysHeader
import com.terning.feature.calendar.calendar.model.CalendarModel
import com.terning.feature.calendar.calendar.model.CalendarModel.Companion.getLocalDateByPage
import com.terning.feature.calendar.calendar.model.CalendarModel.Companion.getYearMonthByPage
import com.terning.feature.calendar.calendar.model.CalendarUiState
import com.terning.feature.calendar.list.CalendarListRoute
import com.terning.feature.calendar.month.CalendarMonthRoute
import com.terning.feature.calendar.week.CalendarWeekRoute
import com.terning.feature.intern.navigation.navigateIntern
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.time.YearMonth

@Composable
fun CalendarRoute(
    navigateUp: () -> Unit,
    navigateToAnnouncement: (Long) -> Unit,
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val calendarUiState by viewModel.uiState.collectAsStateWithLifecycle(lifecycleOwner = lifecycleOwner)

    BackHandler {
        if (calendarUiState.isWeekEnabled) {
            viewModel.updateSelectedDate(calendarUiState.selectedDate)
        } else if (calendarUiState.isListEnabled) {
            viewModel.updateListVisibility(false)
        } else {
            navigateUp()
        }
    }

    CalendarScreen(
        calendarUiState = calendarUiState,
        navigateToAnnouncement = navigateToAnnouncement,
        viewModel = viewModel
    )
}

@Composable
private fun CalendarScreen(
    modifier: Modifier = Modifier,
    calendarUiState: CalendarUiState,
    navigateToAnnouncement: (Long) -> Unit,
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val calendarModel = remember { CalendarModel() }

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = calendarModel.initialPage
    )

    var currentPage by rememberSaveable { mutableIntStateOf(listState.firstVisibleItemIndex) }
    LaunchedEffect(key1 = listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect {
                currentPage = listState.firstVisibleItemIndex
            }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            val coroutineScope = rememberCoroutineScope()
            CalendarTopAppBar(
                date = getYearMonthByPage(currentPage),
                isListExpanded = calendarUiState.isListEnabled,
                isWeekExpanded = calendarUiState.isWeekEnabled,
                onListButtonClicked = {
                    viewModel.updateListVisibility(!calendarUiState.isListEnabled)
                    if (calendarUiState.isWeekEnabled) {
                        viewModel.updateWeekVisibility(false)
                    }
                },
                onMonthNavigationButtonClicked = { direction ->
                    coroutineScope.launch {
                        listState.animateScrollToItem(
                            index = listState.firstVisibleItemIndex + direction,
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        ScreenTransition(
            targetState = !calendarUiState.isListEnabled,
            transitionOne = slideInHorizontally { fullWidth -> -fullWidth } togetherWith
                    slideOutHorizontally { fullWidth -> fullWidth },
            transitionTwo = slideInHorizontally { fullWidth -> fullWidth } togetherWith
                    slideOutHorizontally { fullWidth -> -fullWidth },
            contentOne = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = paddingValues.calculateTopPadding())
                ) {
                    WeekDaysHeader()

                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Grey200
                    )

                    ScreenTransition(
                        targetState = !calendarUiState.isWeekEnabled,
                        transitionOne = slideInVertically { fullHeight -> -fullHeight } togetherWith
                                slideOutVertically { fullHeight -> fullHeight },
                        transitionTwo = slideInVertically { fullHeight -> fullHeight } togetherWith
                                slideOutVertically { fullHeight -> -fullHeight },
                        contentOne = {
                            CalendarMonthRoute(
                                listState = listState,
                                pages = calendarModel.pageCount,
                                selectedDate = calendarUiState.selectedDate,
                                updateSelectedDate = { newDate -> viewModel.updateSelectedDate(newDate) },
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(White),
                            )
                        },
                        contentTwo = {
                            CalendarWeekRoute(
                                calendarUiState = calendarUiState,
                                modifier = Modifier
                                    .fillMaxSize(),
                                navigateToAnnouncement = navigateToAnnouncement,
                                updateSelectedDate = { newDate ->
                                    viewModel.updateSelectedDate(newDate)
                                }
                            )
                        }
                    )
                }
            },
            contentTwo = {
                CalendarListRoute(
                    listState = listState,
                    pages = calendarModel.pageCount,
                    navigateToAnnouncement = navigateToAnnouncement,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = paddingValues.calculateTopPadding())
                )
            },
        )
    }
}

