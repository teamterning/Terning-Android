package com.terning.feature.calendar.calendar

import androidx.activity.compose.BackHandler
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.terning.core.designsystem.component.topappbar.CalendarTopAppBar
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.extension.toast
import com.terning.feature.calendar.calendar.component.ScreenTransition
import com.terning.feature.calendar.calendar.component.WeekDaysHeader
import com.terning.feature.calendar.calendar.model.CalendarState
import com.terning.feature.calendar.month.CalendarMonthScreen
import com.terning.feature.calendar.list.CalendarListScreen
import com.terning.feature.calendar.week.CalendarWeekScreen
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.time.YearMonth

@Composable
fun CalendarRoute(
    navController: NavController
) {
    CalendarScreen(
        navController = navController
    )
}

@Composable
private fun CalendarScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val calendarUiState by viewModel.uiState.collectAsStateWithLifecycle(lifecycleOwner = lifecycleOwner)



    val state by remember { mutableStateOf(CalendarState()) }

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = state.getInitialPage()
    )

    var currentDate by rememberSaveable { mutableStateOf(YearMonth.now()) }
    var currentPage by rememberSaveable { mutableIntStateOf(listState.firstVisibleItemIndex) }

    LaunchedEffect(viewModel.calendarSideEffect, lifecycleOwner) {
        viewModel.calendarSideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is CalendarSideEffect.ShowToast -> context.toast(sideEffect.message)
                }
            }
    }

    LaunchedEffect(key1 = listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect {
                val swipeDirection =
                    (listState.firstVisibleItemIndex - currentPage).coerceIn(-1, 1).toLong()
                currentDate = currentDate.plusMonths(swipeDirection)
                currentPage = listState.firstVisibleItemIndex
            }
    }

    BackHandler {
        if (calendarUiState.isWeekEnabled) {
            viewModel.updateSelectedDate(calendarUiState.selectedDate)
        } else if (calendarUiState.isListEnabled) {
            viewModel.changeListVisibility()
        } else {
            navController.navigateUp()
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            val coroutineScope = rememberCoroutineScope()
            CalendarTopAppBar(
                date = currentDate,
                isListExpanded = calendarUiState.isListEnabled,
                isWeekExpanded = calendarUiState.isWeekEnabled,
                onListButtonClicked = {
                    viewModel.changeListVisibility()
                    if (calendarUiState.isWeekEnabled) {
                        viewModel.disableWeekCalendar()
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
                            CalendarMonthScreen(
                                modifier = Modifier.fillMaxSize(),
                                calendarUiState = calendarUiState,
                                onDateSelected = {
                                    viewModel.updateSelectedDate(it)
                                },
                                listState = listState,
                                pages = state.getPageCount(),
                            )
                        },
                        contentTwo = {
                            CalendarWeekScreen(
                                calendarUiState = calendarUiState,
                                viewModel = viewModel,
                                navController = navController,
                                modifier = Modifier
                                    .fillMaxSize(),
                            )
                        }
                    )
                }
            },
            contentTwo = {
                CalendarListScreen(
                    listState = listState,
                    pages = state.getPageCount(),
                    viewModel = viewModel,
                    navController = navController,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = paddingValues.calculateTopPadding())
                )
            },
        )
    }
}

