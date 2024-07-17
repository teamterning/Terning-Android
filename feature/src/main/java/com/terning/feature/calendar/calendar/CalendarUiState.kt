package com.terning.feature.calendar.calendar

import java.time.LocalDate

data class CalendarUiState(
    val selectedDate: LocalDate,
    val isListEnabled: Boolean = false,
    val isWeekEnabled: Boolean = false,
    val isScrapButtonClicked: Boolean = false,
    val scrapId: Int = -1
)
