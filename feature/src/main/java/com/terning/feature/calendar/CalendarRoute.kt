package com.terning.feature.calendar

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.terning.core.designsystem.theme.Grey200
import com.terning.feature.R
import com.terning.feature.calendar.component.CalendarTopBar
import com.terning.feature.calendar.component.WeekDaysHeader
import com.terning.feature.calendar.models.CalendarState
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun CalendarRoute() {
    CalendarScreen()
}

@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val selectedDate by viewModel.selectedDate.collectAsStateWithLifecycle()
    val state by remember { mutableStateOf(CalendarState()) }

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = state.getInitialPage()
    )

    var currentDate by remember { mutableStateOf(LocalDate.now()) }
    var currentPage by remember { mutableIntStateOf(listState.firstVisibleItemIndex) }

    var isListExpanded by remember { mutableStateOf(false) }

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
        if (selectedDate.isEnabled) {
            viewModel.updateSelectedDate(selectedDate.selectedDate)
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            val coroutineScope = rememberCoroutineScope()
            CalendarTopBar(
                date = currentDate,
                isListExpanded = isListExpanded,
                onListButtonClicked = { isListExpanded = !isListExpanded },
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
        AnimatedContent(
            targetState = isListExpanded,
            transitionSpec = {
                if (!targetState) {
                    slideInHorizontally { fullWidth -> -fullWidth } togetherWith
                            slideOutHorizontally { fullWidth -> fullWidth }
                } else {
                    slideInHorizontally { fullWidth -> fullWidth } togetherWith
                            slideOutHorizontally { fullWidth -> -fullWidth }
                }.using(
                    sizeTransform = SizeTransform(clip = true)
                )
            },
            label = "List Transition"
        ) {
            if (it) {
                CalendarScrapList(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = paddingValues.calculateTopPadding()),
                    date = currentDate,
                    scrapList = viewModel.mockScrapList
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = paddingValues.calculateTopPadding())
                ) {
                    WeekDaysHeader()
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(color = Grey200)
                    )

                    AnimatedContent(
                        targetState = selectedDate.isEnabled,
                        transitionSpec = {
                            if (!targetState) {
                                slideInVertically { fullHeight -> -fullHeight } togetherWith
                                        slideOutVertically { fullHeight -> fullHeight }
                            } else {
                                slideInVertically { fullHeight -> fullHeight } togetherWith
                                        slideOutVertically { fullHeight -> -fullHeight }
                            }.using(
                                sizeTransform = SizeTransform(clip = true)
                            )
                        },
                        label = stringResource(id = R.string.calendar_animation_label)
                    ) { targetState ->
                        if (!targetState) {
                            CalendarMonths(
                                modifier = Modifier.fillMaxSize(),
                                selectedDate = selectedDate,
                                onDateSelected = {
                                    viewModel.updateSelectedDate(it)
                                },
                                listState = listState,
                                pages = state.getPageCount(),
                                scrapLists = viewModel.mockScrapList,
                            )
                        } else {
                            CalendarWeekWithScrap(
                                modifier = Modifier
                                    .fillMaxSize(),
                                selectedDate = selectedDate,
                                scrapLists = viewModel.mockScrapList,
                                onDateSelected = {
                                    viewModel.updateSelectedDate(it)
                                }
                            )
                        }
                    }
                }
            }
        }

    }
}


