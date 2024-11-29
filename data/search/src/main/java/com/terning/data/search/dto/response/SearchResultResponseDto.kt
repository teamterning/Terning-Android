package com.terning.data.search.dto.response

import kotlinx.serialization.SerialName

@Serializable
data class SearchResultResponseDto(
    @SerialName("totalPages")
    val totalPages: Int,
    @SerialName("totalCount")
    val totalCount: Int,
    @SerialName("hasNext")
    val hasNext: Boolean,
    @SerialName("announcements")
    val announcements: List<SearchAnnouncementDto>,
) {
    @Serializable
    data class SearchAnnouncementDto(
        @SerialName("internshipAnnouncementId")
        val internshipAnnouncementId: Long,
        @SerialName("title")
        val title: String,
        @SerialName("dDay")
        val dDay: String,
        @SerialName("workingPeriod")
        val workingPeriod: String,
        @SerialName("companyImage")
        val companyImage: String,
        @SerialName("isScrapped")
        val isScrapped: Boolean,
        @SerialName("deadline")
        val deadline: String,
        @SerialName("startYearMonth")
        val startYearMonth: String,
        @SerialName("color")
        val color: String?,
    )
}
