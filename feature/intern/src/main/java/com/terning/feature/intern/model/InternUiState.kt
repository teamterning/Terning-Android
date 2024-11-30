package com.terning.feature.intern.model

import androidx.compose.ui.graphics.Color
import com.terning.core.designsystem.state.UiState
import com.terning.core.designsystem.theme.CalRed
import com.terning.domain.intern.entity.InternInfo

data class InternUiState(
    val loadState: UiState<InternInfo> = UiState.Loading,
    val isColorChange: Boolean = false,
    val selectedColor: Color = CalRed,
    val scrapCancelDialogVisibility: Boolean = false,
    val internDialogVisibility: Boolean = false,
    val isScrappedState: Boolean = false,
    val showWeb: Boolean = false,
    val internshipModel: InternInfo? = null,
)