package com.terning.data.calendar.mapper

import com.terning.data.calendar.dto.response.CalendarMonthListResponseDto
import com.terning.domain.calendar.entity.CalendarScrapDetail

fun CalendarMonthListResponseDto.toCalendarScrapDetailList(): List<CalendarScrapDetail> = announcements.map { announcement ->
    CalendarScrapDetail(
        internshipAnnouncementId = announcement.internshipAnnouncementId,
        title = announcement.title,
        dDay = announcement.dDay,
        workingPeriod = announcement.workingPeriod,
        color = announcement.color,
        companyImage = announcement.companyImage,
        startYearMonth = announcement.startYearMonth,
        deadline = announcement.deadline,
        isScrapped = announcement.isScrapped
    )
}