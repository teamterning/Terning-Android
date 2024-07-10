package com.terning.feature.calendar.models

import java.time.LocalDate

data class SelectedDateState(
    val selectedDate: LocalDate,
    val isEnabled: Boolean
)
