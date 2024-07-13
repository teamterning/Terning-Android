package com.terning.feature.calendar.calendar

import java.time.LocalDate

data class SelectedDateState(
    val selectedDate: LocalDate,
    val isEnabled: Boolean
)
