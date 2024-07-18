package com.terning.data.dto.request

import com.terning.domain.entity.request.ChangeFilteringRequestModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChangeFilterRequestDto(
    @SerialName("grade")
    val grade: Int,
    @SerialName("workingPeriod")
    val workingPeriod: Int,
    @SerialName("startYear")
    val startYear: Int,
    @SerialName("startMonth")
    val startMonth: Int,
)

fun ChangeFilteringRequestModel.toChangeFilterRequestDto(): ChangeFilterRequestDto =
    ChangeFilterRequestDto(
        grade = grade,
        workingPeriod = workingPeriod,
        startYear = startYear,
        startMonth = startMonth,
    )
