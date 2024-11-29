package com.terning.data.home.dto.request

import com.terning.domain.entity.request.ChangeFilteringRequestModel
import kotlinx.serialization.SerialName

@Serializable
data class ChangeFilterRequestDto(
    @SerialName("grade")
    val grade: String,
    @SerialName("workingPeriod")
    val workingPeriod: String,
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
