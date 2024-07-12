package com.terning.core.designsystem.component.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun TerningBasicDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(
        usePlatformDefaultWidth = false,
        decorFitsSystemWindows = true,
        dismissOnBackPress = true,
        dismissOnClickOutside = true,
    ),
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = properties,
    ) {
        content()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TerningBasicDialogPreview() {
    TerningBasicDialog(
        onDismissRequest = {},
        content = {
            ScrapDialogContent(
                onDismissRequest = {},
                isScrapped = false,
                internInfoList = listOf()
            )
        }
    )
}