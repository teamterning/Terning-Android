package com.terning.data.search.mapper

import com.terning.data.search.dto.response.SearchResultResponseDto
import com.terning.domain.search.entity.SearchResult

fun SearchResultResponseDto.toSearchResultList(): List<SearchResult> {
    return announcements.map {
        SearchResult(
            internshipAnnouncementId = it.internshipAnnouncementId,
            title = it.title,
            dDay = it.dDay,
            workingPeriod = it.workingPeriod,
            companyImage = it.companyImage,
            isScrapped = it.isScrapped,
            deadline = it.deadline,
            startYearMonth = it.startYearMonth,
            color = it.color
        )
    }
}