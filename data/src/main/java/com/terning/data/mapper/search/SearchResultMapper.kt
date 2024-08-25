package com.terning.data.mapper.search

import com.terning.data.dto.response.SearchResultResponseDto
import com.terning.domain.entity.search.SearchResult


fun SearchResultResponseDto.toSearchResultList(): List<SearchResult> {
    return announcements.map {
        SearchResult(
            internshipAnnouncementId = it.internshipAnnouncementId,
            title = it.title,
            dDay = it.dDay,
            workingPeriod = it.workingPeriod,
            companyImage = it.companyImage,
            scrapId = it.scrapId,
            deadline = it.deadline,
            startYearMonth = it.startYearMonth,
            color = it.color
        )
    }
}