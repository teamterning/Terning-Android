package com.terning.data.calendar.dto.request

import kotlinx.serialization.SerialName

@Serializable
data class CalendarMonthListRequestDto(
    @SerialName("deadline")
    val year: Int,
    @SerialName("scraps")
    val month: Int
)

