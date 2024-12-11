package com.terning.feature.calendar.calendar.state

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.compositionLocalOf
import com.terning.feature.calendar.calendar.model.DayModel
import com.terning.feature.calendar.calendar.model.TerningCalendarModel

data class CalendarUiState(
    val selectedDate: DayModel = DayModel(),
    val calendarModel: TerningCalendarModel = TerningCalendarModel(),
    val isListEnabled: Boolean = false,
    val isWeekEnabled: Boolean = false,
)

val LocalPagerState = compositionLocalOf<PagerState> {
    error("No PagerState provided")
}
