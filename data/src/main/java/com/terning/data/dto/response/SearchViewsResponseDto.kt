package com.terning.data.dto.response

import com.terning.domain.entity.response.InternViewsResponseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchViewsResponseDto(
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
        val accountments: List<SearchViewsData>,
    )

    @Serializable
    data class SearchViewsData(
        @SerialName("internshipAnnouncementId")
        val internshipAnnouncementId: Long,
        @SerialName("companyImage")
        val companyImage: String,
        @SerialName("title")
        val title: String,
    )

    fun toSearchViewsEntity(): List<InternViewsResponseModel> = result.accountments.map {
        InternViewsResponseModel(
            title = it.title,
            companyImage = it.companyImage,
            announcementId = it.internshipAnnouncementId
        )
    }
}
