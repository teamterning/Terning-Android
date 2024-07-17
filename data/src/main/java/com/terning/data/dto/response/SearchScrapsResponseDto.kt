package com.terning.data.dto.response

import InternshipAnnouncement
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SearchScrapsResponseDto(
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

    fun toSearchScrapsEntity(): List<InternshipAnnouncement> {
        return announcements.map {
            InternshipAnnouncement(
                announcementId = it.internshipAnnouncementId,
                companyImage = it.companyImage,
                title = it.title,
            )
        }
    }
}


