package com.terning.core.designsystem.component.topappbar

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.terning.core.R
import com.terning.core.designsystem.theme.TerningTypography

@Composable
fun MyPageTopAppBar() {
    TerningTopAppBar(
        showBackButton = false,
        actions = listOf(
            {},
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.my_page_top_app_bar),
                        style = TerningTypography().button3,
                        textAlign = TextAlign.Center
                    )
                    IconButton(onClick = {
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_right),
                            contentDescription = stringResource(id = R.string.ic_20_right)
                        )
                    }
                }
            }
        )
    )
}
