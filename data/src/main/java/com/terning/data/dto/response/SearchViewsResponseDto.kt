package com.terning.data.dto.response

import com.terning.domain.entity.response.InternshipAnnouncement
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchViewsResponseDto(
    @SerialName("internshipAnnouncementId")
    val internshipAnnouncementId: Long,
    @SerialName("companyImage")
    val companyImage: String,
    @SerialName("title")
    val title: String,
) {


    fun toSearchViewsEntity(): List<InternshipAnnouncement> {
        return listOf(
            InternshipAnnouncement(
                announcementId = internshipAnnouncementId,
                companyImage = companyImage,
                title = title,
            )
        )
    }
}