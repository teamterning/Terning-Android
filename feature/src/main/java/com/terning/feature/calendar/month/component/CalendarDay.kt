package com.terning.feature.calendar.month.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.noRippleClickable
import com.terning.feature.calendar.month.model.DayModel
import java.time.LocalDate

@Composable
fun CalendarDay(
    modifier: Modifier = Modifier,
    dayData: DayModel,
    isSelected: Boolean,
    isToday: Boolean,
    onDateSelected: (LocalDate) -> Unit = {}
) {
    val backgroundColor =
        if (isSelected) TerningMain else if (isToday) Grey200 else Color.Transparent
    val textColor =
        if (dayData.isOutDate) {
            Grey150
        } else {
            if (isSelected)
                White
            else
                Black
        }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .noRippleClickable {
                    if (!dayData.isOutDate) {
                        onDateSelected(dayData.date)
                    }
                }
                .background(
                    color = backgroundColor,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = dayData.date.dayOfMonth.toString(),
                color = textColor,
                style = TerningTheme.typography.calendar
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarDayPreview() {
    TerningPointTheme {
        Row {
            CalendarDay(
                dayData = DayModel(LocalDate.now(), false),
                isSelected = true,
                isToday = true,
                onDateSelected = {}
            )
            CalendarDay(
                dayData = DayModel(LocalDate.now(), false),
                isSelected = false,
                isToday = true,
                onDateSelected = {}
            )
            CalendarDay(
                dayData = DayModel(LocalDate.now(), false),
                isSelected = false,
                isToday = false,
                onDateSelected = {}
            )
        }
    }
}