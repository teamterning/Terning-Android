package com.terning.domain.home.entity

data class HomeRecommendedIntern(
    val totalCount: Int,
    val internshipAnnouncementId: Long,
    val companyImage: String,
    val dDay: String,
    val title: String,
    val workingPeriod: String,
    val isScrapped: Boolean,
    val color: String?,
    val deadline: String,
    val startYearMonth: String,
)