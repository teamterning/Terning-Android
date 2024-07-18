package com.terning.data.dto.response

import com.terning.domain.entity.response.HomeFilteringInfoModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeFilteringInfoResponseDto(
    @SerialName("grade")
    val grade: Int?,
    @SerialName("workingPeriod")
    val workingPeriod: Int?,
    @SerialName("startYear")
    val startYear: Int?,
    @SerialName("startMonth")
    val startMonth: Int?,
) {
    fun toHomeFilteringInfoModel(): HomeFilteringInfoModel =
        HomeFilteringInfoModel(
            grade = this.grade,
            workingPeriod = this.workingPeriod,
            startYear = this.startYear,
            startMonth = this.startMonth,
        )
}