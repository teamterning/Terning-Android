package com.terning.feature.home.model

import androidx.compose.ui.graphics.Color
import com.terning.core.designsystem.theme.CalRed

data class HomeDialogState(
    val isColorChange: Boolean = false,
    val isPaletteOpen: Boolean = false,
    val selectedColor: Color = CalRed,
    val isScrapDialogVisible: Boolean = false,
    val isScrappedState: Boolean = false,
    val isToday: Boolean = false,
)