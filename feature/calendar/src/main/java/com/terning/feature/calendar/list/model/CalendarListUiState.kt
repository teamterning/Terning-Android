package com.terning.feature.calendar.list.model

import com.terning.core.designsystem.state.UiState
import com.terning.domain.calendar.entity.CalendarScrapDetail
import java.time.LocalDate

data class CalendarListUiState(
    val loadState: UiState<Map<String, List<CalendarScrapDetail>>> = UiState.Loading,
    val currentDate: LocalDate = LocalDate.now(),
    val scrapCancelDialogVisibility: Boolean = false,
    val scrapDetailDialogVisibility: Boolean = false,
    val internshipAnnouncementId: Long? = null,
    val internshipModel: CalendarScrapDetail? = null
)