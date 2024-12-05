package com.terning.feature.calendar.month.model

import com.terning.core.designsystem.state.UiState
import com.terning.domain.calendar.entity.CalendarScrap

data class CalendarMonthUiState(
    val loadState: UiState<Map<String, List<CalendarScrap>>> = UiState.Loading
)