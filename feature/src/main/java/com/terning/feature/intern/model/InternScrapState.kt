package com.terning.feature.intern.model

import com.terning.core.state.UiState

data class InternScrapState(
    val isScrap: UiState<Boolean> = UiState.Loading,
)