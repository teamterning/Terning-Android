package com.terning.domain.entity.response

data class InternInfoModel(
    val dDay: String,
    val title: String,
    val deadline: String,
    val workingPeriod: String,
    val startDate: String,
    val scrapCount: Int,
    val viewCount: Int,
    val company: String,
    val companyCategory: String,
    val companyImage: String,
    val qualification: String,
    val jobType: String,
    val detail: String,
    val url: String,
    val isScrapped: Boolean,
)