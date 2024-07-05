package com.terning.feature.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.SundayRed
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.R

@Composable
fun WeekDaysHeader(
    modifier: Modifier = Modifier,
) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(
                    horizontal = 37.dp,
                    vertical = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            val dayOfWeek = listOf(
                R.string.calendar_text_sunday,
                R.string.calendar_text_monday,
                R.string.calendar_text_tuesday,
                R.string.calendar_text_wednesday,
                R.string.calendar_text_thursday,
                R.string.calendar_text_friday,
                R.string.calendar_text_saturday,)
            dayOfWeek.forEach { day ->
                Text(
                    modifier = Modifier,
                    text = stringResource(id = day),
                    style = TerningTheme.typography.body7,
                    color = if(day == R.string.calendar_text_sunday) SundayRed else Black,
                    fontSize = 13.sp
                )
            }
        }
}

@Preview(showBackground = true)
@Composable
fun WeekDaysHeaderPreview() {
    TerningTheme{
        WeekDaysHeader()
    }
}