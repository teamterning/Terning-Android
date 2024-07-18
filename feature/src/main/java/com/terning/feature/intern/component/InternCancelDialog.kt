package com.terning.feature.intern.component

import androidx.compose.runtime.Composable
import com.terning.core.designsystem.component.dialog.ScrapCancelDialogContent
import com.terning.core.designsystem.component.dialog.TerningBasicDialog


@Composable
fun InternCancelDialog(
    onDismissRequest: () -> Unit,
    onClickScrapCancel: () -> Unit,
) {
    TerningBasicDialog(
        onDismissRequest = onDismissRequest
    ) {
        ScrapCancelDialogContent(
            onClickScrapCancel = onClickScrapCancel
        )
    }
}
