package com.terning.feature.calendar.calendar.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.terning.core.designsystem.component.dialog.ScrapCancelDialogContent
import com.terning.core.designsystem.component.dialog.TerningBasicDialog
import com.terning.core.designsystem.theme.TerningPointTheme

@Composable
fun CalendarCancelDialog(
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


@Preview(showBackground = true)
@Composable
fun TerningBasicDialogPreview() {
    TerningPointTheme {
        TerningBasicDialog(
            onDismissRequest = {},
        ) {
            ScrapCancelDialogContent()
        }
    }
}