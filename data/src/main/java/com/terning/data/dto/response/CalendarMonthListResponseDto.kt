package com.terning.data.dto.response

import com.terning.domain.entity.response.CalendarScrapDetailModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalendarMonthListResponseDto(
    @SerialName("deadline")
    val deadline: String,
    @SerialName("scraps")
    val scraps: List<Scrap>
) {
    @Serializable
    data class Scrap(
        @SerialName("scrapId")
        val scrapId: Long,
        @SerialName("internshipAnnouncementId")
        val internshipAnnouncementId: Long,
        @SerialName("title")
        val title: String,
        @SerialName("dDay")
        val dDay: String,
        @SerialName("workingPeriod")
        val workingPeriod: String,
        @SerialName("color")
        val color: String,
        @SerialName("companyImage")
        val companyImage: String,
        @SerialName("startYear")
        val startYear: Int,
        @SerialName("startMonth")
        val startMonth: Int
    )

    fun toScrapDetailModelList(): List<CalendarScrapDetailModel> = scraps.map { scrap ->
        CalendarScrapDetailModel(
            scrapId = scrap.scrapId,
            internshipAnnouncementId = scrap.internshipAnnouncementId,
            title = scrap.title,
            dDay = scrap.dDay,
            workingPeriod = scrap.workingPeriod,
            color = scrap.color,
            companyImage = scrap.companyImage,
            startYear = scrap.startYear,
            startMonth = scrap.startMonth,
            deadLine = deadline
        )
    }
}