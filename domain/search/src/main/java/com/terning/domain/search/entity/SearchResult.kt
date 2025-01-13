package com.terning.domain.search.entity

data class SearchResult(
    val totalCount: Int,
    val internshipAnnouncementId: Long,
    val title: String,
    val dDay: String,
    val workingPeriod: String,
    val companyImage: String,
    val isScrapped: Boolean,
    val deadline: String,
    val startYearMonth: String,
    val color: String?,
)
