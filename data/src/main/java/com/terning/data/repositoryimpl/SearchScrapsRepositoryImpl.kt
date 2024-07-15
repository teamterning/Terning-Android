package com.terning.data.repositoryimpl

import com.terning.data.datasource.SearchScrapsDataSource
import com.terning.domain.entity.response.InternScrapsResponseModel
import com.terning.domain.repository.SearchScrapsRepository
import javax.inject.Inject

class SearchScrapsRepositoryImpl @Inject constructor(
    private val searchScrapsDataSource: SearchScrapsDataSource,
) : SearchScrapsRepository {
    override suspend fun getSearchScrapsList(): Result<List<InternScrapsResponseModel>> =
        runCatching {
            searchScrapsDataSource.getSearchScraps().toSearchScrapsEntity()
        }
}