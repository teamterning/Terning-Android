package com.terning.feature.calendar.month

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.terning.core.designsystem.theme.White
import com.terning.core.state.UiState
import com.terning.domain.entity.response.CalendarScrapModel
import com.terning.feature.calendar.calendar.model.CalendarUiState
import com.terning.feature.calendar.calendar.CalendarViewModel
import com.terning.feature.calendar.calendar.model.CalendarDefaults.flingBehavior
import com.terning.feature.calendar.calendar.model.CalendarState.Companion.getDateByPage
import com.terning.feature.calendar.month.component.CalendarMonth
import com.terning.feature.calendar.month.model.MonthData
import kotlinx.coroutines.flow.distinctUntilChanged
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarMonthScreen(
    pages: Int,
    listState: LazyListState,
    calendarUiState: CalendarUiState,
    onDateSelected: (LocalDate) -> Unit,
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
                onDateSelected = onDateSelected,
                selectedDate = calendarUiState.selectedDate,
                modifier = modifier
            )
        }
    }
}

@Composable
fun HorizontalCalendar(
    pages: Int,
    listState: LazyListState,
    isWeekEnabled: Boolean,
    selectedDate: LocalDate,
    scrapMap: Map<String, List<CalendarScrapModel>>,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier
            .background(White),
        state = listState,
        userScrollEnabled = true,
        flingBehavior = flingBehavior(
            state = listState
        )
    ) {
        items(pages) { page ->
            val date = getDateByPage(page)
            val monthData = MonthData(YearMonth.of(date.year, date.month))

            CalendarMonth(
                modifier = Modifier.fillParentMaxSize(),
                onDateSelected = onDateSelected,
                monthData = monthData,
                scrapMap = scrapMap,
                selectedDate = selectedDate,
                isWeekEnabled = isWeekEnabled
            )
        }
    }
}
