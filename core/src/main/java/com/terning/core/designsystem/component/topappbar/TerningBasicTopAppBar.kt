package com.terning.core.designsystem.component.topappbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.terning.core.R
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TerningBasicTopAppBar(
    title: String = "",
    showBackButton: Boolean = false,
    actions: List<@Composable () -> Unit> = emptyList(),
    onBackButtonClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {

            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = TerningTheme.typography.title2
            )

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
                actions.getOrNull(0)?.invoke()
            }
        },
        actions = {
            actions.drop(1).forEach { action ->
                action()
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(White),
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}