package com.terning.feature.calendar.models

import androidx.compose.runtime.Immutable
import java.time.YearMonth

@Immutable
data class MonthModel(
    val yearMonth: YearMonth,
    val weekDays: List<List<DayModel>>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MonthModel

        if (yearMonth != other.yearMonth) return false
        if (weekDays.first().first() != other.weekDays.first().first()) return false
        if (weekDays.last().last() != other.weekDays.last().last()) return false

        return true
    }

    override fun hashCode(): Int {
        var result = yearMonth.hashCode()
        result = 31 * result + weekDays.first().first().hashCode()
        result = 31 * result + weekDays.last().last().hashCode()
        return result
    }

    override fun toString(): String {
        return "CalendarMonth { " +
                "first = ${weekDays.first().first()}, " +
                "last = ${weekDays.last().last()} " +
                "} "
    }
}
