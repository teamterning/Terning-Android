package com.terning.feature.component.topappbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.terning.feature.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TerningTopAppBar(
    title: String,
    showBackButton: Boolean = false,
    customActions: List<@Composable (() -> Unit)>? = null,
    onBackButtonClick: (() -> Unit)? = null,
) {
    TopAppBar(
        title = {
            Text(text = title, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = {
                    onBackButtonClick?.invoke()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_btn_back),
                        contentDescription = stringResource(id = R.string.ic_btn_back)
                    )
                }
            }
        },
        actions = {
            customActions?.forEach { customAction ->
                customAction()
            }
        },
    )
}
