package com.terning.core.designsystem.component.bottomsheet

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.terning.core.designsystem.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TerningBasicBottomSheet(
    content: @Composable () -> Unit,
    onDismissRequest: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState()
) {
    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        },
        sheetState = sheetState,
        containerColor = White,
    ) {
        content()
    }
}