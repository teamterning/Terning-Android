package com.terning.feature.component.topappbar

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.terning.core.designsystem.theme.TerningTypography
import com.terning.feature.R

@Composable
fun MyPageTopAppBar() {
    TerningTopAppBar(
        showBackButton = false,
        customActions = listOf(
            {},
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "프로필 수정",
                        style = TerningTypography().button3,
                        textAlign = TextAlign.Center
                    )
                    IconButton(onClick = {
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_20_right),
                            contentDescription = stringResource(id = R.string.ic_20_right)
                        )
                    }
                }
            }
        )
    )
}
