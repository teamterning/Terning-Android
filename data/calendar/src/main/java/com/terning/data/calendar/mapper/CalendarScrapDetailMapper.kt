package com.terning.data.calendar.mapper

import com.terning.data.calendar.dto.response.CalendarDayListResponseDto
import com.terning.domain.entity.calendar.CalendarScrapDetail

fun CalendarDayListResponseDto.toCalendarScrapDetail() =
    CalendarScrapDetail(
        internshipAnnouncementId = internshipAnnouncementId,
        title = title,
        dDay = dDay,
        workingPeriod = workingPeriod,
        color = color,
        companyImage = companyImage,
        startYearMonth = startYearMonth,
        deadline = deadline,
        isScrapped = isScrapped
    )
