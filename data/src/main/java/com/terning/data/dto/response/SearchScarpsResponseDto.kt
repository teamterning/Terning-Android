package com.terning.data.dto.response

import com.terning.domain.entity.response.InternAnnouncementResponseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchScarpsResponseDto(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("result")
    val result: Result,
) {
    @Serializable
    data class Result(
        @SerialName("accountments")
        val accountments: List<SearchScrapsData>,
    )

    @Serializable
    data class SearchScrapsData(
        @SerialName("internshipAnnouncementId")
        val internshipAnnouncementId: Long,
        @SerialName("companyImage")
        val companyImage: String,
        @SerialName("title")
        val title: String,
    )

    fun toSearchScrapsEntity(): List<InternAnnouncementResponseModel> = result.accountments.map {
        InternAnnouncementResponseModel(
            title = it.title,
            companyImage = it.companyImage,
            announcementId = it.internshipAnnouncementId
        )
    }
}
