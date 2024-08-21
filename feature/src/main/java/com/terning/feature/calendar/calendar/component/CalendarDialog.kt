package com.terning.feature.calendar.calendar.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.terning.core.extension.getFullDateStringInKorean
import com.terning.feature.calendar.calendar.CalendarViewModel
import com.terning.feature.calendar.calendar.model.CalendarUiState
import com.terning.feature.intern.navigation.navigateIntern

@Composable
fun CalendarDialog(
    isWeekScreen: Boolean,
    navigateToAnnouncement: (Long) -> Unit,
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(lifecycleOwner)

    if (uiState.isScrapButtonClicked) {
        CalendarCancelDialog(
            onDismissRequest = { viewModel.updateScrapCancelDialogVisible() },
            onClickScrapCancel = {
                viewModel.deleteScrap(isWeekScreen)
            }
        )
    }

    if (uiState.isInternshipClicked) {
        CalendarDetailDialog(
            deadline = uiState.selectedDate.getFullDateStringInKorean(),
            scrapDetailModel = uiState.internshipModel,
            onDismissRequest = {viewModel.updateInternDialogVisible(false)},
            onClickChangeColorButton = { newColor ->
                viewModel.patchScrap(newColor, isWeekScreen)
            },
            onClickNavigateButton = navigateToAnnouncement
        )
    }
}