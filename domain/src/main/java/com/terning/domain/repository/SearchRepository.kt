package com.terning.domain.repository

import com.terning.domain.entity.response.SearchResultModel
import com.terning.domain.entity.response.InternshipAnnouncementModel

interface SearchRepository {
    suspend fun getSearchList(
        query: String,
        sortBy: String,
        page: Int,
        size: Int,
    ): Result<List<SearchResultModel>>
    suspend fun getSearchViewsList(): Result<List<InternshipAnnouncementModel>>
    suspend fun getSearchScrapsList(): Result<List<InternshipAnnouncementModel>>
}