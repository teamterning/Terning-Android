package com.terning.feature.calendar.month

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
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
import com.terning.core.designsystem.extension.toast
import com.terning.core.designsystem.state.UiState
import com.terning.core.designsystem.theme.White
import com.terning.domain.calendar.entity.CalendarScrap
import com.terning.feature.calendar.calendar.model.DayModel
import com.terning.feature.calendar.calendar.model.LocalPagerState
import com.terning.feature.calendar.calendar.model.TerningCalendarModel.Companion.LocalCalendarModel
import com.terning.feature.calendar.calendar.component.pager.CalendarMonthPager

@Composable
fun CalendarMonthRoute(
    selectedDate: DayModel,
    updateSelectedDate: (DayModel) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CalendarMonthViewModel = hiltViewModel()
) {
    val calendarModel = LocalCalendarModel.current
    val pagerState = LocalPagerState.current
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(lifecycleOwner)

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
            .collect { currentPage ->
                val localDate = calendarModel.getLocalDateByPage(currentPage)
                viewModel.getScrapMonth(localDate)
            }
    }

    CalendarMonthPager(
        modifier = modifier
            .fillMaxSize()
            .background(White),
        pagerState = pagerState,
        calendarModel = calendarModel,
        selectedDate = selectedDate,
        onDateSelect = updateSelectedDate,
        isWeekEnabled = false,
        scrapMap = when (uiState.loadState) {
            UiState.Loading -> emptyMap()
            UiState.Empty -> emptyMap()
            is UiState.Failure -> emptyMap()
            is UiState.Success -> (uiState.loadState as UiState.Success<Map<String, List<CalendarScrap>>>).data
        },
    )
}


