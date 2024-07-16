package com.terning.data.repositoryimpl

import com.terning.data.datasource.SearchDataSource
import com.terning.domain.entity.response.InternshipAnnouncement
import com.terning.domain.repository.SearchRepository
import javax.inject.Inject

class SearchViewsRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource,
) : SearchRepository {
    override suspend fun getSearchViewsList(): Result<List<InternshipAnnouncement>> =
        runCatching {
            searchDataSource.getSearchViews().result.toSearchViewsEntity()
        }
}
