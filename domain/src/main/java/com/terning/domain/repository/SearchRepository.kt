package com.terning.domain.repository

import com.terning.domain.entity.response.SearchResultModel
import com.terning.domain.entity.response.SearchAnnouncement

interface SearchRepository {
    suspend fun getSearchList(
        query: String,
        sortBy: String,
        page: Int,
        size: Int,
    ): Result<List<SearchResultModel>>
    suspend fun getSearchViewsList(): Result<List<SearchAnnouncement>>
    suspend fun getSearchScrapsList(): Result<List<SearchAnnouncement>>
}