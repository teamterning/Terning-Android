package com.terning.data.scrap.dto.request

import kotlinx.serialization.SerialName

@Serializable
data class ScrapColorRequestDto(
    @SerialName("color")
    val color: String? = null,
)