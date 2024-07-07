package com.terning.feature.calendar

import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.terning.core.designsystem.theme.Grey200
import com.terning.feature.calendar.models.CalendarState
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val selectedDate by viewModel.selectedDate.collectAsState()
    val state by remember{ mutableStateOf(CalendarState()) }

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = state.getInitialPage()
    )

    var currentDate by remember { mutableStateOf(selectedDate) }
    var currentPage by remember { mutableIntStateOf(listState.firstVisibleItemIndex)}

    var isListExpanded by remember { mutableStateOf(false) }
    var isWeekEnabled by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect{
                val swipeDirection = (listState.firstVisibleItemIndex - currentPage).coerceIn(-1, 1).toLong()
                currentDate = currentDate.plusMonths(swipeDirection)
                currentPage = listState.firstVisibleItemIndex
            }
    }

    LaunchedEffect(key1 = selectedDate) {
        isWeekEnabled = true
    }

    BackHandler {
        isWeekEnabled = false
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            WeekDaysHeader()
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Grey200)
            )
            CalendarMonths(
                modifier = Modifier.fillMaxSize(),
                selectedDate = selectedDate,
                onDateSelected = {
                    viewModel.updateSelectedDate(it)
                },
                listState = listState,
                pages = state.getPageCount(),
                scrapLists = viewModel.mockScrapList
            )
        }

    }
}
