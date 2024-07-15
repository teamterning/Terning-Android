package com.terning.data.dto.response

import com.terning.domain.entity.response.InternScrapsResponseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchScrapsResponseDto(
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

    fun toSearchScrapsEntity(): List<InternScrapsResponseModel> = result.accountments.map {
        InternScrapsResponseModel(
            title = it.title,
            companyImage = it.companyImage,
            announcementId = it.internshipAnnouncementId
        )
    }
}
