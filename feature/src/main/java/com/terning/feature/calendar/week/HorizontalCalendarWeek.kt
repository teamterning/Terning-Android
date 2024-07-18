package com.terning.feature.calendar.week

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
import com.terning.feature.calendar.calendar.CalendarUiState
import com.terning.feature.calendar.month.component.CalendarDay
import com.terning.feature.calendar.month.model.MonthData
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun HorizontalCalendarWeek(
    calendarUiState: CalendarUiState,
    modifier: Modifier = Modifier,
    onDateSelected: (LocalDate) -> Unit = {}
) {
    val date = calendarUiState.selectedDate
    val monthData = MonthData(YearMonth.of(date.year, date.monthValue))
    val currentWeek = date.getWeekIndexContainingSelectedDate(monthData.inDays)

    val pagerState = rememberPagerState(
        initialPage = currentWeek,
        pageCount = { monthData.totalDays / 7 }
    )

    HorizontalPager(
        modifier = modifier,
        state = pagerState
    ) { page ->
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items(items = monthData.calendarMonth.weekDays[page]) { day ->
                CalendarDay(
                    dayData = day,
                    isSelected = calendarUiState.selectedDate == day.date && calendarUiState.isWeekEnabled,
                    isToday = day.date.isToday(),
                    onDateSelected = onDateSelected
                )
            }
        }
    }
}