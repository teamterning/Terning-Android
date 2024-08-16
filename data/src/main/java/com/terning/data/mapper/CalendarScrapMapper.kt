package com.terning.data.mapper

import com.terning.data.dto.response.CalendarMonthResponseDto
import com.terning.domain.entity.CalendarScrap

fun CalendarMonthResponseDto.toCalendarScrapList(): List<CalendarScrap> = scraps.map { scrap ->
    CalendarScrap(
        scrapId = scrap.scrapId,
        title = scrap.title,
        color = scrap.color,
        deadLine = deadline,
        isScrapped = true
    )
}