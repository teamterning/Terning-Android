package com.terning.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalendarMonthListResponseDto(
    @SerialName("deadline")
    val deadline: String,
    @SerialName("scraps")
    val scraps: List<Scrap>
) {
    @Serializable
    data class Scrap(
        @SerialName("scrapId")
        val scrapId: Long,
        @SerialName("internshipAnnouncementId")
        val internshipAnnouncementId: Long,
        @SerialName("title")
        val title: String,
        @SerialName("dDay")
        val dDay: String,
        @SerialName("workingPeriod")
        val workingPeriod: String,
        @SerialName("color")
        val color: String,
        @SerialName("companyImage")
        val companyImage: String,
        @SerialName("startYear")
        val startYear: Int,
        @SerialName("startMonth")
        val startMonth: Int
    )
}