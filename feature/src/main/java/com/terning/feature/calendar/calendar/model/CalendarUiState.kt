package com.terning.feature.calendar.calendar.model

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.compositionLocalOf
import java.time.LocalDate

data class CalendarUiState(
    val selectedDate: LocalDate = LocalDate.now(),
    val calendarModel: CalendarModel = CalendarModel(),
    val currentPage: Int = 0,
    val isListEnabled: Boolean = false,
    val isWeekEnabled: Boolean = false
)

val LocalPagerState = compositionLocalOf<PagerState> {
    error("No PagerState provided")
}
