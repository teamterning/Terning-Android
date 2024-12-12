package com.terning.feature.calendar.calendar.model

data class CalendarUiState(
    val selectedDate: DayModel = DayModel(),
    val calendarModel: TerningCalendarModel = TerningCalendarModel(),
    val isListEnabled: Boolean = false,
    val isWeekEnabled: Boolean = false,
)