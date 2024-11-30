package com.terning.data.filtering.dto.request

import kotlinx.serialization.SerialName

@Serializable
data class FilteringRequestDto(
    @SerialName("grade")
    val grade: String,
    @SerialName("workingPeriod")
    val workingPeriod: String,
    @SerialName("startYear")
    val startYear: Int,
    @SerialName("startMonth")
    val startMonth: Int
)