package com.terning.data.home.dto.response

import kotlinx.serialization.SerialName

@Serializable
data class HomeFilteringInfoResponseDto(
    @SerialName("grade")
    val grade: String?,
    @SerialName("workingPeriod")
    val workingPeriod: String?,
    @SerialName("startYear")
    val startYear: Int?,
    @SerialName("startMonth")
    val startMonth: Int?,
)