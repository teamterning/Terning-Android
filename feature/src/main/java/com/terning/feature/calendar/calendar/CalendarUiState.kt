package com.terning.feature.calendar.calendar

import com.terning.domain.entity.response.CalendarScrapDetailModel
import java.time.LocalDate

data class CalendarUiState(
    val selectedDate: LocalDate,
    val isListEnabled: Boolean = false,
    val isWeekEnabled: Boolean = false,
    val isScrapButtonClicked: Boolean = false,
    val isInternshipClicked: Boolean = false,
    val internshipModel: CalendarScrapDetailModel? = null,
    val scrapId: Long = -1
)
