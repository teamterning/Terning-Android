package com.terning.data.repositoryimpl

import com.terning.data.datasource.SearchDataSource
import com.terning.data.dto.request.SearchRequestDto
import com.terning.data.mapper.search.toSearchPopularAnnouncementList
import com.terning.data.mapper.search.toSearchResultList
import com.terning.domain.entity.search.SearchPopularAnnouncement
import com.terning.domain.entity.search.SearchResult
import com.terning.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource,
) : SearchRepository {
    override suspend fun getSearchList(
        query: String,
        sortBy: String,
        page: Int,
        size: Int,
    ): Result<List<SearchResult>> {
        return runCatching {
            searchDataSource.getSearch(
                SearchRequestDto(
                    keyword = query,
                    sortBy = sortBy,
                    page = page,
                    size = size
                )
            ).result.toSearchResultList()
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
}