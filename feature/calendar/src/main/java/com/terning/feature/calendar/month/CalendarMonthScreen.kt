package com.terning.feature.calendar.month

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
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
import com.terning.core.designsystem.state.UiState
import com.terning.core.designsystem.extension.toast
import com.terning.feature.calendar.calendar.model.CalendarModel.Companion.getLocalDateByPage
import com.terning.feature.calendar.calendar.model.LocalPagerState
import com.terning.feature.calendar.month.component.CalendarMonth
import com.terning.feature.calendar.month.model.CalendarMonthUiState
import com.terning.feature.calendar.month.model.MonthModel
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarMonthRoute(
    selectedDate: LocalDate,
    updateSelectedDate: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CalendarMonthViewModel = hiltViewModel()
) {
    val pagerState = LocalPagerState.current
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val monthUiState by viewModel.uiState.collectAsStateWithLifecycle(lifecycleOwner)

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is CalendarMonthSideEffect.ShowToast -> context.toast(sideEffect.message)
                }
            }
    }

    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect { currentPage->
                viewModel.getScrapMonth(currentPage)
            }
    }

    CalendarMonthScreen(
        pagerState = pagerState,
        selectedDate = selectedDate,
        uiState = monthUiState,
        updateSelectedDate = updateSelectedDate,
        modifier = modifier
    )
}

@Composable
private fun CalendarMonthScreen(
    pagerState: PagerState,
    selectedDate: LocalDate,
    uiState: CalendarMonthUiState,
    updateSelectedDate: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxSize()
    ) {page ->
        val date = getLocalDateByPage(page)
        val monthModel = MonthModel(YearMonth.of(date.year, date.month))

        CalendarMonth(
            modifier = Modifier.fillMaxSize(),
            onDateSelected = updateSelectedDate,
            monthModel = monthModel,
            selectedDate = selectedDate,
            isWeekEnabled = false,
            scrapMap = when (uiState.loadState) {
                UiState.Loading -> emptyMap()
                UiState.Empty -> emptyMap()
                is UiState.Failure -> emptyMap()
                is UiState.Success -> uiState.loadState.data
            },
        )
    }
}


