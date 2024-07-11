package com.terning.feature.home.changefilter.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningTheme

@Composable
fun FilteringSubTitleText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = TerningTheme.typography.body5,
        color = Grey300,
        modifier = modifier,
    )
}