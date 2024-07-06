package com.terning.feature.component.topappbar

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.terning.feature.R

@Composable
fun LogoTopAppBar() {
    TerningTopAppBar(
        showBackButton = false,
        customActions = listOf {
            Icon(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(id = R.string.ic_logo),
            )
        }
    )
}
