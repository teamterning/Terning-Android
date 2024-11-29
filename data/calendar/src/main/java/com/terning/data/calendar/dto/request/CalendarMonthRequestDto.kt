package com.terning.data.calendar.dto.request

import kotlinx.serialization.SerialName

@Serializable
data class CalendarMonthRequestDto(
    @SerialName("year")
    val year: Int,
    @SerialName("month")
    val month: Int
)
