package com.terning.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeRecommendInternResponseDto(
    @SerialName("scrapId")
    val scrapId: Long?,
    @SerialName("intershipAnnouncementId")
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
)
