package com.terning.feature.calendar.calendar.model

import com.terning.domain.entity.CalendarScrapDetail
import java.time.LocalDate

data class CalendarUiState(
    val selectedDate: LocalDate = LocalDate.now(),
    val isListEnabled: Boolean = false,
    val isWeekEnabled: Boolean = false
)
