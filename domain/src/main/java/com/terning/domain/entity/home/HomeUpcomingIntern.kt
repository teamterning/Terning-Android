package com.terning.domain.entity.home

data class HomeUpcomingIntern(
    val hasScrapped: Boolean,
    val homeUpcomingInternDetail: List<HomeUpcomingInternDetail>
) {
    data class HomeUpcomingInternDetail(
        val internshipAnnouncementId: Long,
        val companyImage: String,
        val title: String,
        val dDay: String,
        val deadline: String,
        val workingPeriod: String,
        val isScrapped: Boolean,
        val color: String,
        val startYearMonth: String,
        val companyInfo: String,
    )
}
