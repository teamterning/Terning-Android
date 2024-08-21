package com.terning.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResultResponseDto(
    @SerialName("totalPages")
    val totalPages: Int,
    @SerialName("hasNext")
    val hasNext: Boolean,
    @SerialName("announcements")
    val announcements: List<SearchAnnouncementDto>,
) {
    @Serializable
    data class SearchAnnouncementDto(
        @SerialName("internshipAnnouncementId")
        val internshipAnnouncementId: Long,
        @SerialName("scrapId")
        val scrapId: Long?,
        @SerialName("dDay")
        val dDay: String,
        @SerialName("companyImage")
        val companyImage: String,
        @SerialName("title")
        val title: String,
        @SerialName("workingPeriod")
        val workingPeriod: String,
        @SerialName("startYearMonth")
        val startYearMonth: String,
        @SerialName("color")
        val color: String?,
        @SerialName("deadline")
        val deadline: String,
    )
}