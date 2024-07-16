package com.terning.domain.entity.response

data class CalendarScrapDetailModel(
    val scrapId: Long,
    val internshipAnnouncementId: Long,
    val title: String,
    val dDay: String,
    val workingPeriod: String,
    val color: String,
    val companyImage: String,
    val startYear: Int,
    val startMonth: Int,
    val deadLine: String,
    val isScrapped: Boolean = true
)
