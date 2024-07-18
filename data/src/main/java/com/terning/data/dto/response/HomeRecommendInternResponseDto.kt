package com.terning.data.dto.response

import com.terning.domain.entity.response.HomeRecommendInternModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeRecommendInternResponseDto(
    @SerialName("scrapId")
    val scrapId: Long?,
    @SerialName("intershipAnnouncementId")
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
) {
    fun toRecommendInternEntity(): HomeRecommendInternModel =
        HomeRecommendInternModel(
            scrapId = this.scrapId,
            internshipAnnouncementId = this.internshipAnnouncementId,
            title = this.title,
            dDay = this.dDay,
            workingPeriod = this.workingPeriod,
            companyImage = this.companyImage,
            isScrapped = this.isScrapped,
        )
}
