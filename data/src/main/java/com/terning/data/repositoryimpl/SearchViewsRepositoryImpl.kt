package com.terning.data.repositoryimpl

import com.terning.data.datasource.SearchViewsDataSource
import com.terning.domain.entity.response.InternViewsResponseModel
import com.terning.domain.repository.SearchViewsRepository
import javax.inject.Inject

class SearchViewsRepositoryImpl @Inject constructor(
    private val searchViewsDataSource: SearchViewsDataSource,
) : SearchViewsRepository {
    override suspend fun getSearchViewsList(): Result<List<InternViewsResponseModel>> =
        runCatching {
            searchViewsDataSource.getSearchViews().toSearchViewsEntity()
        }
}