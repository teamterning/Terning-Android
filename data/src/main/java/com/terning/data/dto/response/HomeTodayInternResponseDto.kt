package com.terning.data.dto.response

import com.terning.domain.entity.response.HomeTodayInternModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeTodayInternResponseDto(
    @SerialName("scrapId")
    val scrapId: Long,
    @SerialName("internshipAnnouncementId")
    val internshipAnnouncementId: Long,
    @SerialName("companyImage")
    val companyImage: String,
    @SerialName("title")
    val title: String,
    @SerialName("dDay")
    val dDay: String,
    @SerialName("deadline")
    val deadline: String,
    @SerialName("workingPeriod")
    val workingPeriod: String,
    @SerialName("color")
    val color: String,
    @SerialName("startYearMonth")
    val startYearMonth: String,

) {
    fun toHomeTodayInternList(): HomeTodayInternModel =
        HomeTodayInternModel(
            scrapId = this.scrapId,
            internshipAnnouncementId = this.internshipAnnouncementId,
            companyImage = this.companyImage,
            title = this.title,
            dDay = this.dDay,
            deadline = this.deadline,
            workingPeriod = this.workingPeriod,
            startYearMonth = this.startYearMonth,
            color = this.color,
        )
}
