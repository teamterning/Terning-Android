package com.terning.data.repositoryimpl

import com.terning.data.datasource.SearchDataSource
import com.terning.data.dto.request.SearchRequestDto
import com.terning.domain.entity.response.InternshipAnnouncementModel
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
                    query = query,
                    sortBy = sortBy,
                    page = page,
                    size = size
                )
            ).result.toSearchResultEntity()
        }
    }

    override suspend fun getSearchViewsList(): Result<List<InternshipAnnouncementModel>> =
        runCatching {
            searchDataSource.getSearchViews().result.toSearchViewsEntity()
        }

    override suspend fun getSearchScrapsList(): Result<List<InternshipAnnouncementModel>> =
        runCatching {
            searchDataSource.getSearchScraps().result.toSearchScrapsEntity()
        }
}