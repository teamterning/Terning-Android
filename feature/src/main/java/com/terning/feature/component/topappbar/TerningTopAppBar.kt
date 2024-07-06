package com.terning.feature.component.topappbar

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.terning.core.designsystem.theme.TerningTypography
import com.terning.feature.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TerningTopAppBar(
    title: String = "",
    showBackButton: Boolean = false,
    customActions: List<@Composable (() -> Unit)>? = null,
    onBackButtonClick: (() -> Unit)? = null,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = TerningTypography().title2,
            )
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
        }
    )
}
