package com.terning.feature.calendar.month

import com.terning.core.state.UiState
import com.terning.domain.entity.response.ScrapModel

data class ScrapCalendarState (
    val loadState: UiState<Map<String, List<ScrapModel>>> = UiState.Loading
)