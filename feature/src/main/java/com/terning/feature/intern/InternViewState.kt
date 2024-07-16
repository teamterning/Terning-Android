package com.terning.feature.intern

import androidx.compose.ui.graphics.Color
import com.terning.core.designsystem.theme.CalRed
import com.terning.core.state.UiState
import com.terning.domain.entity.response.InternInfoModel

data class InternViewState(
    var internInfo: UiState<InternInfoModel> = UiState.Loading,
    val isScrap: Boolean = false,
    val isColorChange: Boolean = false,
    val selectedColor: Color = CalRed,
    val isScrapDialogVisible: Boolean = false,
    val isScrappedState: Boolean = false,
)