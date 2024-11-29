package com.terning.feature.calendar.month.model

import com.terning.core.state.UiState
import com.terning.domain.entity.calendar.CalendarScrap

data class CalendarMonthUiState(
    val loadState: UiState<Map<String, List<CalendarScrap>>> = UiState.Loading
)