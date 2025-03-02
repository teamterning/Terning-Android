package com.terning.data.calendar.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalendarMonthRequestDto(
    @SerialName("year")
    val year: Int,
    @SerialName("month")
    val month: Int
)
