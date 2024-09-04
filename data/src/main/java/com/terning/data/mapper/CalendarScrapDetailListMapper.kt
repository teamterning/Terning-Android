package com.terning.data.mapper

import com.terning.data.dto.response.CalendarMonthListResponseDto
import com.terning.domain.entity.calendar.CalendarScrapDetail

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