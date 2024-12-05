package com.terning.feature.intern.model

import com.terning.core.designsystem.state.UiState

data class InternScrapState(
    val isScrap: UiState<Boolean> = UiState.Loading,
)