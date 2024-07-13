package com.terning.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScrapRequestDto(
    @SerialName("year")
    val year: Int,
    @SerialName("month")
    val month: Int
)
