package com.terning.data.mapper.search

import com.terning.data.dto.response.SearchAnnouncementResponseDto
import com.terning.domain.entity.response.SearchAnnouncement

fun SearchAnnouncementResponseDto.toSearchAnnouncementList(): List<SearchAnnouncement> {
    return announcements.map {
        SearchAnnouncement(
            announcementId = it.internshipAnnouncementId,
            companyImage = it.companyImage,
            title = it.title,
        )
    }
}