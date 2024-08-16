package com.terning.data.mapper

import com.terning.data.dto.response.CalendarDayListResponseDto
import com.terning.domain.entity.CalendarScrapDetail

fun CalendarDayListResponseDto.toCalendarScrapDetail() =
    CalendarScrapDetail(
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
