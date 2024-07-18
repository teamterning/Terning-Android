package com.terning.domain.entity.response

data class HomeRecommendInternModel(
    val scrapId: Long?,
    val internshipAnnouncementId: Long,
    val title: String,
    val dDay: String,
    val workingPeriod: String,
    val companyImage: String,
    val isScrapped: Boolean,
)
