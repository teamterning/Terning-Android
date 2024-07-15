package com.terning.feature.intern.model

import androidx.compose.ui.graphics.Color
import com.terning.core.designsystem.theme.CalRed

data class InternScrapState(
    val isScrap: Boolean = false,
    val isColorChange: Boolean = false,
    val selectedColor: Color = CalRed,
    val isScrapDialogVisible: Boolean = false,
    val isScrapped: Boolean = false,
    val viewCount: Int = 0,
)