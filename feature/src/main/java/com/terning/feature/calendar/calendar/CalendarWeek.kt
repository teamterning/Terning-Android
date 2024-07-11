package com.terning.feature.calendar.calendar

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
import com.terning.feature.calendar.models.Scrap
import com.terning.feature.calendar.models.SelectedDateState
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarWeek(
    selectedDate: SelectedDateState,
    modifier: Modifier = Modifier,
    scrapLists: List<List<Scrap>> = listOf(),
    onDateSelected: (LocalDate) -> Unit = {}
) {
    val date = selectedDate.selectedDate
    val monthData = MonthData(YearMonth.of(date.year, date.monthValue))
    val currentWeek = date.getWeekIndexContainingSelectedDate()

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
                .padding(horizontal = 32.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items(items = monthData.calendarMonth.weekDays[page]) { day ->
                CalendarDay(
                    dayData = day,
                    isSelected = selectedDate.selectedDate == day.date && selectedDate.isEnabled,
                    isToday = day.date.isToday(),
                    onDateSelected = onDateSelected
                )
            }
        }
    }
}