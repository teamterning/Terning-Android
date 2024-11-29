package com.terning.domain.search.repository

import com.terning.domain.entity.search.SearchResult
import com.terning.domain.entity.search.SearchPopularAnnouncement

interface SearchRepository {
    suspend fun getSearchList(
        query: String,
        sortBy: String,
        page: Int,
        size: Int,
    ): Result<List<SearchResult>>
    suspend fun getSearchViewsList(): Result<List<SearchPopularAnnouncement>>
    suspend fun getSearchScrapsList(): Result<List<SearchPopularAnnouncement>>
}