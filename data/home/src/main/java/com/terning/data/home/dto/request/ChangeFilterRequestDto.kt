package com.terning.data.home.dto.request

import com.terning.domain.home.entity.ChangeFilteringRequestModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
