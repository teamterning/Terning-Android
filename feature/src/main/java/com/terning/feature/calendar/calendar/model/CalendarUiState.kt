package com.terning.feature.calendar.calendar.model

import java.time.LocalDate

data class CalendarUiState(
    val selectedDate: LocalDate = LocalDate.now(),
    val calendarModel: CalendarModel = CalendarModel(),
    val currentPage: Int = 0,
    val isListEnabled: Boolean = false,
    val isWeekEnabled: Boolean = false
)
