package com.terning.data.repositoryimpl

import com.terning.data.datasource.SearchDataSource
import com.terning.data.dto.request.SearchRequestDto
import com.terning.data.mapper.search.toSearchAnnouncementList
import com.terning.domain.entity.response.SearchAnnouncement
import com.terning.domain.entity.response.SearchResultModel
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
    ): Result<List<SearchResultModel>> {
        return runCatching {
            searchDataSource.getSearch(
                SearchRequestDto(
                    keyword = query,
                    sortBy = sortBy,
                    page = page,
                    size = size
                )
            ).result.toSearchResultEntity()
        }
    }

    override suspend fun getSearchViewsList(): Result<List<SearchAnnouncement>> =
        runCatching {
            searchDataSource.getSearchViews().result.toSearchAnnouncementList()
        }

    override suspend fun getSearchScrapsList(): Result<List<SearchAnnouncement>> =
        runCatching {
            searchDataSource.getSearchScraps().result.toSearchAnnouncementList()
        }
}