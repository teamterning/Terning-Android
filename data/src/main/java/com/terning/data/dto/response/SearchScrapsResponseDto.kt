package com.terning.data.dto.response

import com.terning.domain.entity.response.InternshipAnnouncementModel
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

    fun toSearchScrapsEntity(): List<InternshipAnnouncementModel> {
        return announcements.map {
            InternshipAnnouncementModel(
                announcementId = it.internshipAnnouncementId,
                companyImage = it.companyImage,
                title = it.title,
            )
        }
    }
}


