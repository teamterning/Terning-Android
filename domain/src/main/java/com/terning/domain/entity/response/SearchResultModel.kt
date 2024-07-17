package com.terning.domain.entity.response

data class SearchResultModel(
    val internshipAnnouncementId: Long,
    val title: String,
    val dDay: String,
    val workingPeriod: String,
    val companyImage: String,
    val scrapId: Long?,
)