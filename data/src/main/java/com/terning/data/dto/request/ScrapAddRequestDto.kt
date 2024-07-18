package com.terning.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScrapAddRequestDto(
    @SerialName("color")
    val color: Int,
)