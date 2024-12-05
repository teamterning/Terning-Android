package com.terning.feature.intern.component

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme

@Composable
fun InternPageTitle(
    modifier: Modifier,
    text: String,
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .padding(
                top = 7.dp,
                bottom = 6.dp,
            )
    ) {
        VerticalDivider(
            color = TerningMain,
            thickness = 2.dp,
        )
        Text(
            text = text,
            style = TerningTheme.typography.title4,
            color = Black,
            modifier = modifier.padding(
                start = 8.dp
            )
        )
    }
}