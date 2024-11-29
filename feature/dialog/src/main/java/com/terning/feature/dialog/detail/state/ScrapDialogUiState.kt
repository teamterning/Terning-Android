package com.terning.feature.dialog.detail.state

import com.terning.core.type.ColorType

data class ScrapDialogUiState(
    val initialColorType: ColorType = ColorType.RED,
    val selectedColorType: ColorType = ColorType.RED,
    val isColorChanged: Boolean = false,
)