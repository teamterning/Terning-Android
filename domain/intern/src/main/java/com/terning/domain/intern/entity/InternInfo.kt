package com.terning.domain.intern.entity

data class InternInfo(
    val dDay: String,
    val title: String,
    val deadline: String,
    val workingPeriod: String,
    val startYearMonth: String,
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
    val color: String? = null,
) {
    companion object {
        val EMPTY_INTERN = InternInfo(
            dDay = "",
            title = "",
            viewCount = 0,
            company = "",
            companyCategory = "",
            companyImage = "",
            deadline = "",
            workingPeriod = "",
            startYearMonth = "",
            qualification = "",
            jobType = "",
            url = "",
            detail = "",
            isScrapped = false,
            scrapCount = 0
        )
    }
}