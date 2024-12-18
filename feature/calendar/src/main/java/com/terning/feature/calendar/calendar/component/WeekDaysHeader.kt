package com.terning.feature.calendar.calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.SundayRed
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.calendar.calendar.type.WeekDay

@Composable
fun WeekDaysHeader(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(
                horizontal = 20.dp,
                vertical = 18.dp
            ),
    ) {
        WeekDay.entries.forEach { day ->
            Text(
                modifier = Modifier.weight(1f),
                text = day.nameInKorean,
                style = TerningTheme.typography.body7,
                color = if (WeekDay.isSunday(day)) SundayRed else Black,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeekDaysHeaderPreview() {
    TerningPointTheme {
        WeekDaysHeader()
    }
}