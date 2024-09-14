package com.terning.feature.dialog.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey375
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningTheme

@Composable
fun ScrapInfoRow(title: String, value: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(23.dp, Alignment.Start),
        verticalAlignment = Alignment.Top,
    ) {
        Text(
            text = title,
            style = TerningTheme.typography.body2,
            color = Grey400,
        )
        Text(
            text = value,
            style = TerningTheme.typography.body3,
            color = Grey375,
        )
    }
}