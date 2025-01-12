package com.terning.data.search.repositoryimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.terning.data.search.datasource.SearchDataSource
import com.terning.data.search.mapper.toSearchBannerList
import com.terning.data.search.mapper.toSearchPopularAnnouncementList
import com.terning.data.search.mapper.toSearchResultList
import com.terning.data.search.pagingsource.SearchPagingSource
import com.terning.domain.search.entity.SearchBanner
import com.terning.domain.search.entity.SearchPopularAnnouncement
import com.terning.domain.search.entity.SearchResult
import com.terning.domain.search.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource,
) : SearchRepository {

    override fun getSearchList(
        query: String,
        sortBy: String,
    ): Flow<PagingData<SearchResult>> {
        return Pager(
            PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            )
        ) {
            SearchPagingSource(
                query = query,
                sortBy = sortBy,
                dataSource = searchDataSource
            )
        }.flow.map { pagedData ->
            pagedData.map { it.second.toSearchResultList(it.first) }
        }
    }

    override suspend fun getSearchViewsList(): Result<List<SearchPopularAnnouncement>> =
        runCatching {
            searchDataSource.getSearchViews().result.toSearchPopularAnnouncementList()
        }

    override suspend fun getSearchScrapsList(): Result<List<SearchPopularAnnouncement>> =
        runCatching {
            searchDataSource.getSearchScraps().result.toSearchPopularAnnouncementList()
        }

    override suspend fun getSearchBannersList(): Result<List<SearchBanner>> =
        runCatching {
            searchDataSource.getSearchBanners().result.toSearchBannerList()
        }
}