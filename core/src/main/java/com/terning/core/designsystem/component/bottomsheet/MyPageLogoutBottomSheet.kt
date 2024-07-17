package com.terning.core.designsystem.component.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.R
import com.terning.core.designsystem.theme.TerningTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageLogoutBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onLogoutClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()

    TerningBasicBottomSheet(
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.my_page_logout_title),
                    style = TerningTheme.typography.heading1,
                    modifier = modifier.padding(top = 45.dp)
                )
                Text(text = stringResource(id = R.string.my_page_logout_title))
            }
        },
        onDismissRequest = { onDismiss() },
        sheetState = sheetState
    )
}
