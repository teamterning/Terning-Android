package com.terning.feature.calendar.list.model

import com.terning.core.state.UiState
import com.terning.domain.entity.calendar.CalendarScrapDetail
import java.time.LocalDate

data class CalendarListUiState(
    val loadState: UiState<Map<String, List<CalendarScrapDetail>>> = UiState.Loading,
    val currentDate: LocalDate = LocalDate.now(),
    val scrapDialogVisibility: Boolean = false,
    val internDialogVisibility: Boolean = false,
    val internshipAnnouncementId: Long? = null,
    val internshipModel: CalendarScrapDetail? = null
)