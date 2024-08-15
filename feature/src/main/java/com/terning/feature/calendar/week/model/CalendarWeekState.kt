package com.terning.feature.calendar.week.model

import com.terning.core.state.UiState
import com.terning.domain.entity.CalendarScrapDetailModel

data class CalendarWeekState(
    val loadState: UiState<List<CalendarScrapDetailModel>> = UiState.Loading,
)