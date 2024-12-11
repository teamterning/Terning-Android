package com.terning.feature.calendar.calendar.model

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.compositionLocalOf

data class CalendarUiState(
    val selectedDate: DayModel = DayModel(),
    val calendarModel: TerningCalendarModel = TerningCalendarModel(),
    val isListEnabled: Boolean = false,
    val isWeekEnabled: Boolean = false,
)

val LocalPagerState = compositionLocalOf<PagerState> {
    error("No PagerState provided")
}
