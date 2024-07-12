package com.terning.core.designsystem.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.terning.core.R
import com.terning.core.designsystem.theme.White

@Composable
fun TerningDialog(
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

@Composable
fun DialogContent(
    onDismissRequest: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(30.dp)
            .background(
                color = White,
                shape = RoundedCornerShape(20.dp)
            ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { onDismissRequest() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_dialog_x_32),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TerningDialogPreview() {
    TerningDialog(
        onDismissRequest = {},
        content = {
            DialogContent(
                onDismissRequest = {}
            )
        })
}