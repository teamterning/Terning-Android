package com.terning.data.calendar.dto.response

import kotlinx.serialization.SerialName

@Serializable
data class CalendarMonthListResponseDto(
    @SerialName("deadline")
    val deadline: String,
    @SerialName("announcements")
    val announcements: List<Announcement>
) {
    @Serializable
    data class Announcement(
        @SerialName("internshipAnnouncementId")
        val internshipAnnouncementId: Long,
        @SerialName("companyImage")
        val companyImage: String,
        @SerialName("dDay")
        val dDay: String,
        @SerialName("title")
        val title: String,
        @SerialName("workingPeriod")
        val workingPeriod: String,
        @SerialName("isScrapped")
        val isScrapped: Boolean,
        @SerialName("color")
        val color: String,
        @SerialName("deadline")
        val deadline: String,
        @SerialName("startYearMonth")
        val startYearMonth: String,
    )
}