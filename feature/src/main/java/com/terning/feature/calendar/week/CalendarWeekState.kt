package com.terning.feature.calendar.week

import com.terning.core.state.UiState
import com.terning.domain.entity.response.CalendarScrapDetailModel

data class CalendarWeekState(
    val loadState: UiState<List<CalendarScrapDetailModel>> = UiState.Loading,
)