package com.terning.core.designsystem.topappbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.terning.core.R
import com.terning.core.designsystem.theme.TerningTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TerningTopAppBar(
    title: String = "",
    showBackButton: Boolean = false,
    customActions: List<@Composable () -> Unit> = emptyList(),
    onBackButtonClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            Box(Modifier.fillMaxWidth()) {
                Text(
                    text = title,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    style = TerningTypography().title2
                )
            }
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = {
                    onBackButtonClick.invoke()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = stringResource(id = R.string.ic_back)
                    )
                }
            } else {
                customActions.getOrNull(0)?.invoke()
            }
        },
        actions = {
            customActions.drop(1).forEach { customAction ->
                customAction()
            }
        },
    )
}
