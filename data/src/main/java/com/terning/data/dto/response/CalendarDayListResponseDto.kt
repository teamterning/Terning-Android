package com.terning.data.dto.response

import com.terning.domain.entity.response.CalendarScrapDetailModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalendarDayListResponseDto(
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
){
    fun toScrapDetailModelList(): CalendarScrapDetailModel =
        CalendarScrapDetailModel(
            scrapId = scrapId,
            internshipAnnouncementId = internshipAnnouncementId,
            title = title,
            dDay = dDay,
            workingPeriod = workingPeriod,
            color = color,
            companyImage = companyImage,
            startYear = startYear,
            startMonth = startMonth,
            deadLine = ""
        )

}