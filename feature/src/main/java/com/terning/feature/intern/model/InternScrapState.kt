package com.terning.feature.intern.model

import com.terning.core.state.UiState
import com.terning.domain.entity.response.InternInfoModel

data class InternScrapState(
    val isScrap: UiState<InternInfoModel> = UiState.Loading,
)