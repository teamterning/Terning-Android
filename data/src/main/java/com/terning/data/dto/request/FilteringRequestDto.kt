package com.terning.data.dto.request

import com.terning.domain.entity.request.FilteringRequestModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilteringRequestDto(
    @SerialName("grade")
    val grade: Int,
    @SerialName("workingPeriod")
    val workingPeriod: Int,
    @SerialName("startYear")
    val startYear: Int,
    @SerialName("startMonth")
    val startMonth: Int
)

fun FilteringRequestModel.toFilteringRequestDto(): FilteringRequestDto =
    FilteringRequestDto(
        grade = grade,
        workingPeriod = workingPeriod,
        startYear = startYear,
        startMonth = startMonth
    )