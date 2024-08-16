package com.terning.feature.calendar.month

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.terning.core.state.UiState
import com.terning.feature.calendar.calendar.CalendarViewModel
import com.terning.feature.calendar.calendar.model.CalendarModel.Companion.getDateByPage
import com.terning.feature.calendar.calendar.model.CalendarUiState
import com.terning.feature.calendar.month.component.HorizontalCalendar
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
internal fun CalendarMonthScreen(
    pages: Int,
    listState: LazyListState,
    calendarUiState: CalendarUiState,
    modifier: Modifier = Modifier,
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val scrapState by viewModel.calendarMonthState.collectAsStateWithLifecycle(lifecycleOwner)

    LaunchedEffect(key1 = listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect {
                val page = listState.firstVisibleItemIndex
                val date = getDateByPage(page)
                viewModel.getScrapMonth(date.year, date.monthValue)
            }
    }

    when (scrapState.loadState) {
        UiState.Loading -> {}
        UiState.Empty -> {}
        is UiState.Failure -> {}
        is UiState.Success -> {
            val scrapMap = (scrapState.loadState as UiState.Success).data
            HorizontalCalendar(
                pages = pages,
                listState = listState,
                isWeekEnabled = calendarUiState.isWeekEnabled,
                scrapMap = scrapMap,
                onDateSelected = { viewModel.updateSelectedDate(it) },
                selectedDate = calendarUiState.selectedDate,
                modifier = modifier
            )
        }
    }
}


