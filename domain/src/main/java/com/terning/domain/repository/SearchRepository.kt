package com.terning.domain.repository

import com.terning.domain.entity.search.SearchResult
import com.terning.domain.entity.search.SearchAnnouncement

interface SearchRepository {
    suspend fun getSearchList(
        query: String,
        sortBy: String,
        page: Int,
        size: Int,
    ): Result<List<SearchResult>>
    suspend fun getSearchViewsList(): Result<List<SearchAnnouncement>>
    suspend fun getSearchScrapsList(): Result<List<SearchAnnouncement>>
}