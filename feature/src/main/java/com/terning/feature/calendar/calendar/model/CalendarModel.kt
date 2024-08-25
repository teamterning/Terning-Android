package com.terning.feature.calendar.calendar.model

import androidx.compose.runtime.Immutable
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth

@Immutable
class CalendarModel internal constructor(
    startYear: Int = START_YEAR,
    endYear: Int = END_YEAR
) {
    private val currentDate = LocalDate.now()
    private val startYearDate = LocalDate.of(startYear, 1, 1)
    private val endYearDate = LocalDate.of(endYear, 12, 31)

    val pageCount = (endYearDate.year - startYearDate.year) * 12
    val initialPage = (currentDate.year - startYearDate.year) * 12 + currentDate.monthValue - 1

    companion object {
        const val START_YEAR = 2020
        const val END_YEAR = 2030

        fun getLocalDateByPage(page: Int): LocalDate = LocalDate.of(
            START_YEAR + page / 12,
            page % 12 + 1,
            1
        )

        fun getYearMonthByPage(page: Int): YearMonth = YearMonth.of(
            START_YEAR + page / 12,
            page % 12 + 1,
        )
    }
}