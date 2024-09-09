package com.terning.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalendarDayListResponseDto(
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
        @SerialName("color")
        val color: String,
        @SerialName("companyImage")
        val companyImage: String,
        @SerialName("startYearMonth")
        val startYearMonth: String,
        @SerialName("isScrapped")
        val isScrapped: Boolean
)