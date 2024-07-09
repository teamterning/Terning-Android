package com.terning.core.designsystem.component.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.R
import com.terning.core.designsystem.theme.TerningTheme

@Composable
fun SignUpBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {
    TerningBasicBottomSheet(
        content = {
            Column {
                Text(
                    text = stringResource(id = R.string.bottom_sheet_title),
                    style = TerningTheme.typography.title2,
                    modifier = modifier.padding(start = 28.dp)
                )

            }
        },
        onDismissRequest = { onDismiss() }
    )

}