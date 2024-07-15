package com.terning.feature.calendar.month

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.terning.core.designsystem.theme.White
import com.terning.core.state.UiState
import com.terning.domain.entity.response.ScrapModel
import com.terning.feature.calendar.calendar.CalendarDefaults.flingBehavior
import com.terning.feature.calendar.calendar.CalendarState.Companion.getDateByPage
import com.terning.feature.calendar.calendar.CalendarViewModel
import com.terning.feature.calendar.month.model.MonthData
import com.terning.feature.calendar.calendar.SelectedDateState
import kotlinx.coroutines.flow.distinctUntilChanged
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarMonthScreen(
    pages: Int,
    listState: LazyListState,
    selectedDate: SelectedDateState,
    onDateSelected: (LocalDate) -> Unit,
    viewModel: CalendarViewModel,
    modifier: Modifier = Modifier,
) {
    val scrapState by viewModel.scrapCalendarState.collectAsState()

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
            CalendarMonthList(
                pages = pages,
                modifier = modifier,
                selectedDate = selectedDate,
                onDateSelected = onDateSelected,
                listState = listState,
                scrapMap = (scrapState.loadState as UiState.Success).data
            )
        }
    }
}


@Composable
fun CalendarMonthList(
    pages: Int,
    listState: LazyListState,
    modifier: Modifier = Modifier,
    selectedDate: SelectedDateState,
    onDateSelected: (LocalDate) -> Unit,
    scrapMap: Map<String, List<ScrapModel>>
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
                selectedDate = selectedDate,
                onDateSelected = onDateSelected,
                monthData = monthData,
                scrapMap = scrapMap
            )
        }
    }
}
