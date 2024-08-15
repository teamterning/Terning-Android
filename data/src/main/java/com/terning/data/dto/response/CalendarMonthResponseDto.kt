package com.terning.data.dto.response

import com.terning.domain.entity.CalendarScrap
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalendarMonthResponseDto(
    @SerialName("deadline")
    val deadline: String,
    @SerialName("scraps")
    val scraps: List<Scrap>
) {
    @Serializable
    data class Scrap(
        @SerialName("scrapId")
        val scrapId: Long,
        @SerialName("title")
        val title: String,
        @SerialName("color")
        val color: String
    )

    fun toScrapModelList(): List<CalendarScrap> = scraps.map { scrap ->
        CalendarScrap(
            scrapId = scrap.scrapId,
            title = scrap.title,
            color = scrap.color,
            deadLine = deadline,
            isScrapped = true
        )
    }
}