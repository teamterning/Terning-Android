package com.terning.data.search.dto.response

import kotlinx.serialization.SerialName


@Serializable
data class SearchAnnouncementResponseDto(
    @SerialName("announcements")
    val announcements: List<ScrapsAnnouncementDto>,
) {
    @Serializable
    data class ScrapsAnnouncementDto(
        @SerialName("internshipAnnouncementId")
        val internshipAnnouncementId: Long,
        @SerialName("companyImage")
        val companyImage: String,
        @SerialName("title")
        val title: String,
    )
}