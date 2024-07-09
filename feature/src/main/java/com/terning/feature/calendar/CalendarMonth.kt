package com.terning.feature.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.calendar.models.MonthData
import com.terning.feature.calendar.models.Scrap
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarMonth(
    modifier: Modifier = Modifier,
    monthData: MonthData,
    onDateSelected: (LocalDate) -> Unit,
    selectedDate: LocalDate?,
    scrapLists: List<List<Scrap>> = listOf()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
    ) {
        val month = monthData.calendarMonth.weekDays
        for (week in month) {
            Row(
                modifier = Modifier.weight(1f),
            ) {
                for (day in week) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CalendarDay(
                            dayData = day,
                            isSelected = selectedDate == day.date,
                            isToday = day.date == LocalDate.now(),
                            onDateSelected = onDateSelected
                        )
                        if(!day.isOutDate) {
                            val index = day.date.dayOfWeek.value - 1
                            CalendarScrap(
                                scrapList = scrapLists[index]
                            )
                        }
                    }
                }
            }
            if(month.indexOf(week) != month.lastIndex) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = Grey150)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarMonthPreview() {
    TerningTheme {
        CalendarMonth(
            monthData = MonthData(YearMonth.now()),
            selectedDate = LocalDate.now(),
            onDateSelected = {},
            scrapLists = listOf()
        )
    }
}