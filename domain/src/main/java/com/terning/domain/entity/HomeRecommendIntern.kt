package com.terning.domain.entity

data class HomeRecommendIntern(
    val scrapId: Long?,
    val internshipAnnouncementId: Long,
    val title: String,
    val dDay: String,
    val deadline: String,
    val workingPeriod: String,
    val startYearMonth: String,
    val companyImage: String,
    val isScrapped: Boolean,
)
