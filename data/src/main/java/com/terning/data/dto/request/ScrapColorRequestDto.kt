package com.terning.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScrapColorRequestDto(
    @SerialName("color")
    val color: Int? = null,
)