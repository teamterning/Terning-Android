package com.terning.feature.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terning.core.extension.getWeekIndexContainingSelectedDate
import com.terning.core.extension.isToday
import com.terning.feature.calendar.models.MonthData
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarWeek(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit = {}
) {
    val monthData = MonthData(YearMonth.of(selectedDate.year, selectedDate.monthValue))
    val currentWeek = selectedDate.getWeekIndexContainingSelectedDate()

    val pagerState = rememberPagerState (
        initialPage = currentWeek,
        pageCount = {monthData.totalDays / 7}
    )

    HorizontalPager(
        modifier = modifier,
        state = pagerState) { page ->
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items(items = monthData.calendarMonth.weekDays[page]) { day ->
                CalendarDay(
                    dayData = day,
                    isSelected = selectedDate == day.date,
                    isToday = day.date.isToday(),
                    onDateSelected = onDateSelected
                )
            }
        }
    }
}