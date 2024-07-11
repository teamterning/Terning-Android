package com.terning.core.designsystem.component.topappbar

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.terning.core.R

@Composable
fun LogoTopAppBar(
    modifier: Modifier = Modifier
) {
    TerningBasicTopAppBar(
        showBackButton = false,
        actions = listOf {
            Icon(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(id = R.string.ic_logo),
            )
        },
        modifier = modifier
    )
}
