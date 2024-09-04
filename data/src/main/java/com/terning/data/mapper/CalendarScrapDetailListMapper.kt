package com.terning.data.mapper

import com.terning.data.dto.response.CalendarMonthListResponseDto
import com.terning.domain.entity.calendar.CalendarScrapDetail

fun CalendarMonthListResponseDto.toCalendarScrapDetailList(): List<CalendarScrapDetail> = scraps.map { scrap ->
    CalendarScrapDetail(
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