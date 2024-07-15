package com.terning.feature.intern.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningSub4
import com.terning.core.designsystem.theme.TerningTheme

@Composable
fun InternPageTitle(
    modifier: Modifier,
    text: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 16.dp,
                bottom = 16.dp
            )
            .background(TerningSub4)
    ) {
        Text(
            text = text,
            style = TerningTheme.typography.title4,
            color = TerningMain,
            modifier = modifier.padding(
                top = 7.dp,
                bottom = 6.dp,
                start = 20.dp
            )
        )
    }
}