package com.terning.feature.intern.model

import androidx.compose.ui.graphics.Color
import com.terning.core.designsystem.theme.CalRed
import com.terning.core.state.UiState
import com.terning.domain.entity.intern.InternInfoModel

data class InternUiState(
    val loadState: UiState<InternInfoModel> = UiState.Loading,
    val isColorChange: Boolean = false,
    val isPaletteOpen: Boolean = false,
    val selectedColor: Color = CalRed,
    val isScrapDialogVisible: Boolean = false,
    val isScrappedState: Boolean = false,
    val showWeb: Boolean = false,
)