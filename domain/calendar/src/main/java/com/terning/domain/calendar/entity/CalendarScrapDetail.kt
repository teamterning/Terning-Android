package com.terning.domain.calendar.entity

data class CalendarScrapDetail(
    val internshipAnnouncementId: Long,
    val title: String,
    val dDay: String,
    val workingPeriod: String,
    val deadline: String,
    val color: String,
    val companyImage: String,
    val startYearMonth: String,
    val isScrapped: Boolean
)
