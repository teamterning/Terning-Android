package com.terning.feature.calendar.scrap

import com.terning.core.state.UiState
import com.terning.domain.entity.response.CalendarScrapDetailModel

data class CalendarListState (
    val loadState: UiState<Map<String, List<CalendarScrapDetailModel>>> = UiState.Loading
)