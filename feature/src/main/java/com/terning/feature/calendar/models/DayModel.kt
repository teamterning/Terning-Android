package com.terning.feature.calendar.models

import androidx.compose.runtime.Immutable
import java.time.LocalDate

@Immutable
data class DayModel(
    val date: LocalDate,
    val isOutDate: Boolean = false
)