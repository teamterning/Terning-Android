package com.terning.feature.home.filtering.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.TerningTheme

@Composable
fun FilteringMainTitleText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = TerningTheme.typography.title3,
        color = Black,
        modifier = modifier,
    )
}