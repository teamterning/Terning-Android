package com.terning.domain.search.repository

import androidx.paging.PagingData
import com.terning.domain.search.entity.SearchBanner
import com.terning.domain.search.entity.SearchPopularAnnouncement
import com.terning.domain.search.entity.SearchResult
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun getSearchList(
        query: String,
        sortBy: String,
    ): Flow<PagingData<SearchResult>>

    suspend fun getSearchViewsList(): Result<List<SearchPopularAnnouncement>>
    suspend fun getSearchScrapsList(): Result<List<SearchPopularAnnouncement>>
    suspend fun getSearchBannersList(): Result<List<SearchBanner>>
}