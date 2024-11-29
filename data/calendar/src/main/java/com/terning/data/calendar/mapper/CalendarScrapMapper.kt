package com.terning.data.calendar.mapper

import com.terning.data.calendar.dto.response.CalendarMonthResponseDto
import com.terning.domain.entity.calendar.CalendarScrap

fun CalendarMonthResponseDto.toCalendarScrapList(): List<CalendarScrap> = scraps.map { scrap ->
    CalendarScrap(
        scrapId = scrap.scrapId,
        title = scrap.title,
        color = scrap.color,
        deadLine = deadline,
        isScrapped = true
    )
}