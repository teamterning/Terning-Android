package com.terning.core.designsystem.component.topappbar

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.R
import com.terning.core.designsystem.theme.TerningMain

@Composable
fun LogoTopAppBar(
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val heightRatio = 780f / configuration.screenHeightDp
    val widthRatio = 360f / configuration.screenWidthDp

    TerningBasicTopAppBar(
        showBackButton = false,
        actions = listOf {
            Icon(
                painter = painterResource(id = R.drawable.ic_terning_logo_typo),
                contentDescription = stringResource(id = R.string.ic_logo),
                tint = TerningMain,
                modifier = Modifier
                    .padding(start = 24.dp, end = 16.dp, top = 16.dp)
                    .size(width = (109 * widthRatio).dp, height = (26 * heightRatio).dp)
            )
        },
        modifier = modifier
    )
}
