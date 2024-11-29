package com.terning.core.designsystem.component.topappbar

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.terning.core.R
import com.terning.core.designsystem.theme.TerningTheme

@Composable
fun MyPageTopAppBar(
    modifier: Modifier = Modifier,
) {
    TerningBasicTopAppBar(
        showBackButton = false,
        actions = listOf(
            {},
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.my_page_top_app_bar),
                        style = TerningTheme.typography.button3,
                        textAlign = TextAlign.Center
                    )
                    IconButton(onClick = {}) {
                    }
                }
            }
        ),
        modifier = modifier,
    )
}
