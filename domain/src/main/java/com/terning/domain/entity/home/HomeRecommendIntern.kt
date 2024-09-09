package com.terning.domain.entity.home

data class HomeRecommendIntern(
    val totalCount: Int,
    val homeRecommendInternDetail: List<HomeRecommendInternDetail>
) {
    data class HomeRecommendInternDetail(
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
}
