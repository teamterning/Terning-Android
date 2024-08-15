package com.terning.feature.calendar.month.model

import androidx.compose.runtime.Immutable
import java.time.YearMonth

/**
 * [MonthModel] is responsible for managing a month's overall characteristics
 * it consists of following properties:-
 *
 * [inDays] represents the number of days in the previous month that should be shown before the first day of the month.
 * [outDays] represents the number of days in the next month that should be shown after the last day of the month.]
 * [totalDays] represents the total number of days shown on calendar
 * [calendarMonth] represents the list of days of the month, a list of [DayModel]
 */


@Immutable
data class MonthModel(
    val month: YearMonth
) {
    private val firstDayOfWeek = month.atDay(1).dayOfWeek.value
    private val previousMonth = month.minusMonths(1)
    private val nextMonth = month.plusMonths(1)

    val inDays = firstDayOfWeek % 7
    val monthDays = month.lengthOfMonth()
    val outDays = (7 - ((inDays + monthDays) % 7)) % 7
    val totalDays = monthDays + inDays + outDays

    private val rows = (0 until totalDays).chunked(7)

    val calendarMonth =
        MonthModel(month, rows.map { week -> week.map { dayOffset -> getDay(dayOffset) } })

    private fun getDay(dayOffset: Int): DayModel {
        val firstDayOnCalendar = month.atDay(1).minusDays(inDays.toLong())
        val date = firstDayOnCalendar.plusDays(dayOffset.toLong())
        val isOutDate = YearMonth.of(date.year, date.monthValue) != month

        return DayModel(date, isOutDate)
    }

    @Immutable
    data class MonthModel(
        val yearMonth: YearMonth,
        val weekDays: List<List<DayModel>>
    ) {
        override fun toString(): String {
            return "${yearMonth.year}년 ${yearMonth.monthValue.toString().padStart(2, '0')}월"
        }
    }
}


