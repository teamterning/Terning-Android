package com.terning.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalendarMonthListRequestDto(
    @SerialName("deadline")
    val year: Int,
    @SerialName("scraps")
    val month: Int
)

