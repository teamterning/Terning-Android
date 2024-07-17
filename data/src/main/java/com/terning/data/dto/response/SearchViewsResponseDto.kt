package com.terning.data.dto.response

import InternshipAnnouncementModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchViewsResponseDto(
    @SerialName("announcements")
    val announcements: List<ViewsAnnouncementDto>,
) {
    @Serializable
    data class ViewsAnnouncementDto(
        @SerialName("internshipAnnouncementId")
        val internshipAnnouncementId: Long,
        @SerialName("companyImage")
        val companyImage: String,
        @SerialName("title")
        val title: String,
    )

    fun toSearchViewsEntity(): List<InternshipAnnouncementModel> {
        return announcements.map {
            InternshipAnnouncementModel(
                announcementId = it.internshipAnnouncementId,
                companyImage = it.companyImage,
                title = it.title,
            )
        }
    }
}