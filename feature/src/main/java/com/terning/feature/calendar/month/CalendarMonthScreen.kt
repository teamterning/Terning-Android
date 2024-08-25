package com.terning.feature.calendar.month

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.terning.core.extension.toast
import com.terning.core.state.UiState
import com.terning.domain.entity.CalendarScrap
import com.terning.feature.calendar.calendar.CalendarSideEffect
import com.terning.feature.calendar.calendar.model.CalendarDefaults.flingBehavior
import com.terning.feature.calendar.calendar.model.CalendarModel.Companion.getDateByPage
import com.terning.feature.calendar.month.component.CalendarMonth
import com.terning.feature.calendar.month.model.MonthModel
import com.terning.feature.calendar.month.model.CalendarMonthUiState
import kotlinx.coroutines.flow.distinctUntilChanged
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarMonthRoute(
    listState: LazyListState,
    pages: Int,
    selectedDate: LocalDate,
    updateSelectedDate: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CalendarMonthViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val monthUiState by viewModel.calendarMonthState.collectAsStateWithLifecycle(lifecycleOwner)

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
                val page = listState.firstVisibleItemIndex
                val date = getDateByPage(page)
                viewModel.getScrapMonth(date.year, date.monthValue)
            }
    }

    CalendarMonthScreen(
        selectedDate = selectedDate,
        calendarMonthUiState = monthUiState,
        listState = listState,
        pages = pages,
        updateSelectedDate = updateSelectedDate,
        modifier = modifier
    )
}

@Composable
private fun CalendarMonthScreen(
    listState: LazyListState,
    calendarMonthUiState: CalendarMonthUiState,
    pages: Int,
    selectedDate: LocalDate,
    updateSelectedDate: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (calendarMonthUiState.loadState) {
        UiState.Loading -> {}
        UiState.Empty -> {}
        is UiState.Failure -> {}
        is UiState.Success -> {
            val scrapMap = calendarMonthUiState.loadState.data

            MonthSuccessScreen(
                pages = pages,
                listState = listState,
                scrapMap = scrapMap,
                onDateSelected = updateSelectedDate,
                selectedDate = selectedDate,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun MonthSuccessScreen(
    pages: Int,
    listState: LazyListState,
    selectedDate: LocalDate,
    scrapMap: Map<String, List<CalendarScrap>>,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
        state = listState,
        userScrollEnabled = true,
        flingBehavior = flingBehavior(
            state = listState
        )
    ) {
        items(pages) { page ->
            val date = getDateByPage(page)
            val monthModel = MonthModel(YearMonth.of(date.year, date.month))

            CalendarMonth(
                modifier = Modifier.fillParentMaxSize(),
                onDateSelected = onDateSelected,
                monthModel = monthModel,
                scrapMap = scrapMap,
                selectedDate = selectedDate,
                isWeekEnabled = false
            )
        }
    }
}


