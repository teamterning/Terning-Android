package com.terning.feature.calendar.month.model

import com.terning.core.state.UiState
import com.terning.domain.entity.CalendarScrap

data class CalendarMonthUiState(
    val loadState: UiState<Map<String, List<CalendarScrap>>> = UiState.Loading
)