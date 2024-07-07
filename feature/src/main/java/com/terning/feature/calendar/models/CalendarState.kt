package com.terning.feature.calendar.models

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.time.LocalDate
import java.time.YearMonth

@Stable
class CalendarState internal constructor(
    startMonth: YearMonth = YearMonth.of(START_YEAR, 1),
    endMonth: YearMonth = YearMonth.of(END_YEAR, 12),
    firstVisibleMonth: YearMonth = YearMonth.now(),
) {
    private var _startMonth by mutableStateOf(startMonth)
    var startMonth: YearMonth
        get() = _startMonth
        set(value) {
            if (value != _startMonth) {
                _startMonth = value
            }
        }

    private var _endMonth by mutableStateOf(endMonth)
    var endMonth: YearMonth
        get() = _endMonth
        set(value) {
            if (value != _endMonth) {
                _endMonth = value
            }
        }

    private var _firstVisibleMonth by mutableStateOf(firstVisibleMonth)
    var firstVisibleMonth: YearMonth
        get() = _firstVisibleMonth
        set(value) {
            if (value != _firstVisibleMonth) {
                _firstVisibleMonth = value
            }
        }

    fun getPageCount(): Int = (END_YEAR - START_YEAR) * 12

    fun getInitialPage(): Int =
        (firstVisibleMonth.year - START_YEAR) * 12 + firstVisibleMonth.monthValue - 1

    companion object {
        const val START_YEAR = 2020
        const val END_YEAR = 2030

        fun getDateByPage(page: Int): LocalDate = LocalDate.of(
            START_YEAR + page / 12,
            page % 12 + 1,
            1
        )
    }
}