package com.terning.feature.home.home.model

import androidx.compose.ui.graphics.Color
import com.terning.core.designsystem.theme.CalRed

data class HomeScrapViewState(
    val isColorChange: Boolean = false,
    val isPaletteOpen: Boolean = false,
    val selectedColor: Color = CalRed,
    val isScrapDialogVisible: Boolean = false,
    val isScrappedState: Boolean = false,
)