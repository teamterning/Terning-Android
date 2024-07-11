package com.terning.feature.calendar.calendar

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
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.extension.isToday
import com.terning.feature.calendar.models.MonthData
import com.terning.feature.calendar.models.Scrap
import com.terning.feature.calendar.models.SelectedDateState
import com.terning.feature.calendar.scrap.CalendarScrapStrip
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarMonth(
    modifier: Modifier = Modifier,
    monthData: MonthData,
    onDateSelected: (LocalDate) -> Unit,
    selectedDate: SelectedDateState,
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
                            isSelected = selectedDate.selectedDate == day.date && selectedDate.isEnabled,
                            isToday = day.date.isToday(),
                            onDateSelected = onDateSelected
                        )
                        if(!day.isOutDate) {
                            val index = day.date.dayOfMonth - 1
                            CalendarScrapStrip(
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
    TerningPointTheme {
        CalendarMonth(
            monthData = MonthData(YearMonth.now()),
            selectedDate = SelectedDateState(LocalDate.now(), true),
            onDateSelected = {},
            scrapLists = listOf()
        )
    }
}