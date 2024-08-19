package com.terning.domain.entity

data class HomeTodayInternModel(
    val scrapId: Long,
    val internshipAnnouncementId: Long,
    val companyImage: String,
    val title: String,
    val dDay: String,
    val deadline: String,
    val workingPeriod: String,
    val color: String,
    val startYearMonth: String,
)
