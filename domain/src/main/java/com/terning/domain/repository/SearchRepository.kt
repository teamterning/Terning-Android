package com.terning.domain.repository

import com.terning.domain.entity.response.InternshipAnnouncementModel

interface SearchRepository {
    suspend fun getSearchViewsList(): Result<List<InternshipAnnouncementModel>>
    suspend fun getSearchScrapsList(): Result<List<InternshipAnnouncementModel>>
}