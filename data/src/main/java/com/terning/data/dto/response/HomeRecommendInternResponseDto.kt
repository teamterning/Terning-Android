package com.terning.data.dto.response

import com.terning.domain.entity.HomeRecommendIntern
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
    @SerialName("deadline")
    val deadline: String,
    @SerialName("workingPeriod")
    val workingPeriod: String,
    @SerialName("startYearMonth")
    val startYearMonth: String,
    @SerialName("companyImage")
    val companyImage: String,
    @SerialName("isScrapped")
    val isScrapped: Boolean,
) {
    fun toRecommendInternEntity(): HomeRecommendIntern =
        HomeRecommendIntern(
            scrapId = this.scrapId,
            internshipAnnouncementId = this.internshipAnnouncementId,
            title = this.title,
            dDay = this.dDay,
            deadline = deadline,
            workingPeriod = this.workingPeriod,
            startYearMonth = this.startYearMonth,
            companyImage = this.companyImage,
            isScrapped = this.isScrapped,
        )
}
