package com.terning.feature.calendar.day.model

import androidx.compose.runtime.Immutable
import java.time.LocalDate

@Immutable
data class DayClass(
    val date: LocalDate,
    val isOutDate: Boolean = false
)