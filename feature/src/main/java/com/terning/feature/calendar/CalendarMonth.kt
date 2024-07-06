package com.terning.feature.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.calendar.models.MonthData
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarMonth(
    modifier: Modifier = Modifier,
    monthData: MonthData,
    onDateSelected: (LocalDate) -> Unit,
    selectedDate: LocalDate,
    taskList: List<List<String>> = listOf(
        listOf("Task 1", "Task 2", "Task 3"),
        listOf("Task 4", "Task 5", "Task 6"),
        listOf("Task 7", "Task 8", "Task 9")
    )
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
            taskList = listOf(
                listOf("Task 1", "Task 2", "Task 3"),
                listOf("Task 4", "Task 5", "Task 6"),
                listOf("Task 7", "Task 8", "Task 9")
            )
        )
    }
}