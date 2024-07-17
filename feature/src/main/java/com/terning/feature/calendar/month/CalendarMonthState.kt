package com.terning.feature.calendar.month

import com.terning.core.state.UiState
import com.terning.domain.entity.response.CalendarScrapModel

data class CalendarMonthState (
    val loadState: UiState<Map<String, List<CalendarScrapModel>>> = UiState.Loading
)