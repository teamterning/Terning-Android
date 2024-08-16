package com.terning.feature.calendar.calendar.model

import com.terning.domain.entity.CalendarScrapDetail
import java.time.LocalDate

data class CalendarUiState(
    val selectedDate: LocalDate,
    val isListEnabled: Boolean = false,
    val isWeekEnabled: Boolean = false,
    val isScrapButtonClicked: Boolean = false,
    val isInternshipClicked: Boolean = false,
    val internshipModel: CalendarScrapDetail? = null,
    val scrapId: Long = -1
)
