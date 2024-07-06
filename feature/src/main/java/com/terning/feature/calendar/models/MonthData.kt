package com.terning.feature.calendar.models

import androidx.compose.runtime.Immutable
import java.time.YearMonth

@Immutable
data class MonthData(
    val month: YearMonth
) {
    private val firstDayOfWeek = month.atDay(1).dayOfWeek.value
    private val previousMonth = month.minusMonths(1)
    private val nextMonth = month.plusMonths(1)

    val inDays = firstDayOfWeek % 7
    val monthDays = month.lengthOfMonth()
    val outDays = 7 - ((inDays + monthDays) % 7)
    val totalDays = monthDays + inDays + outDays

    private val rows = (0 until totalDays).chunked(7)

    val calendarMonth = MonthModel(month, rows.map { week -> week.map {dayOffset -> getDay(dayOffset)}})

    private fun getDay(dayOffset: Int): DayModel {
        val firstDayOnCalendar = month.atDay(1).minusDays(inDays.toLong())
        val date = firstDayOnCalendar.plusDays(dayOffset.toLong())
        val isOutDate = YearMonth.of(date.year, date.monthValue) != month

        return DayModel(date, isOutDate)
    }
}
