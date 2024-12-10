package com.terning.feature.calendar.week.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.extension.getWeekIndexContainingSelectedDate
import com.terning.core.designsystem.extension.isToday
import com.terning.feature.calendar.calendar.component.CalendarDay
import com.terning.feature.calendar.calendar.model.TerningCalendarModel.Companion.LocalCalendarModel
import java.time.LocalDate

@Composable
fun HorizontalCalendarWeek(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val calendarModel = LocalCalendarModel.current

    val monthModel = calendarModel.getMonthModelByPage(selectedDate)
    val currentWeek = selectedDate.getWeekIndexContainingSelectedDate(monthModel.inDays)

    val pagerState = rememberPagerState(
        initialPage = currentWeek,
        pageCount = { monthModel.totalDays / 7 }
    )

    LaunchedEffect(selectedDate) {
        pagerState.animateScrollToPage(selectedDate.getWeekIndexContainingSelectedDate(monthModel.inDays))
    }

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
            items(items = monthModel.calendarMonth[page]) { day ->
                CalendarDay(
                    dayData = day,
                    isSelected = day.date == selectedDate,
                    isToday = day.date.isToday(),
                    onDateSelected = onDateSelected
                )
            }
        }
    }
}