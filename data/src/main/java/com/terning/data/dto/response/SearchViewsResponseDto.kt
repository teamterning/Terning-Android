package com.terning.data.dto.response

import com.terning.domain.entity.response.SearchAnnouncement
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

    fun toSearchViewsEntity(): List<SearchAnnouncement> {
        return announcements.map {
            SearchAnnouncement(
                announcementId = it.internshipAnnouncementId,
                companyImage = it.companyImage,
                title = it.title,
            )
        }
    }
}