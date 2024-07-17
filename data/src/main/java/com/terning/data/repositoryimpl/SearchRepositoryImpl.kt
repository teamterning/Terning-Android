package com.terning.data.repositoryimpl

import com.terning.data.datasource.SearchDataSource
import com.terning.domain.entity.response.InternshipAnnouncementModel
import com.terning.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource,
) : SearchRepository {
    override suspend fun getSearchViewsList(): Result<List<InternshipAnnouncementModel>> =
        runCatching {
            searchDataSource.getSearchViews().result.toSearchViewsEntity()
        }

    override suspend fun getSearchScrapsList(): Result<List<InternshipAnnouncementModel>> =
        runCatching {
            searchDataSource.getSearchScraps().result.toSearchScrapsEntity()
        }
}