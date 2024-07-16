package com.terning.data.repositoryimpl

import InternshipAnnouncement
import com.terning.data.datasource.SearchScrapsDataSource
import com.terning.data.datasource.SearchViewsDataSource
import com.terning.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchViewsDataSource: SearchViewsDataSource,
    private val searchScrapsDataSource: SearchScrapsDataSource,
) : SearchRepository {
    override suspend fun getSearchViewsList(): Result<List<InternshipAnnouncement>> {
        return runCatching {
            searchViewsDataSource.getSearchViews().result.toSearchViewsEntity()
        }
    }

    override suspend fun getSearchScrapsList(): Result<List<InternshipAnnouncement>> {
        return runCatching {
            searchScrapsDataSource.getSearchScraps().result.toSearchScrapEntity()
        }
    }
}