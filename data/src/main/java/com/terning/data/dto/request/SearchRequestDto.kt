package com.terning.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchRequestDto(
    @SerialName("query")
    val query: String,
    @SerialName("sortBy")
    val sortBy: String,
    @SerialName("page")
    val page: Int,
    @SerialName("size")
    val size: Int,
)