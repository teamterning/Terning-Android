package com.terning.feature.intern.model

import androidx.compose.ui.graphics.Color
import com.terning.core.designsystem.theme.CalRed
import com.terning.core.state.UiState
import com.terning.domain.entity.CalendarScrapDetail
import com.terning.domain.entity.intern.InternInfo

data class InternUiState(
    val loadState: UiState<InternInfo> = UiState.Loading,
    val isColorChange: Boolean = false,
    val isPaletteOpen: Boolean = false,
    val selectedColor: Color = CalRed,
    val isCancelDialogVisibility: Boolean = false,
    val isScrapDialogVisibility: Boolean = false,
    val isScrappedState: Boolean = false,
    val showWeb: Boolean = false,
    val scrapId: Long? = null,
    val internshipModel: InternInfo? = null
)