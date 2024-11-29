package com.terning.data.home.dto.response

import kotlinx.serialization.SerialName

@Serializable
data class HomeRecommendInternResponseDto(
    @SerialName("totalCount")
    val totalCount: Int,
    @SerialName("result")
    val result: List<Result>
) {
    @Serializable
    data class Result(
        @SerialName("internshipAnnouncementId")
        val internshipAnnouncementId: Long,
        @SerialName("title")
        val title: String,
        @SerialName("dDay")
        val dDay: String,
        @SerialName("deadline")
        val deadline: String,
        @SerialName("workingPeriod")
        val workingPeriod: String,
        @SerialName("startYearMonth")
        val startYearMonth: String,
        @SerialName("companyImage")
        val companyImage: String,
        @SerialName("isScrapped")
        val isScrapped: Boolean,
        @SerialName("color")
        val color: String?,
    )
}
