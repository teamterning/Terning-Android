package com.terning.data.repositoryimpl

import com.terning.data.datasource.SearchScrapsDataSource
import com.terning.domain.entity.response.InternAnnouncementResponseModel
import com.terning.domain.repository.SearchScrapsRepository
import javax.inject.Inject

class SearchScrapsRepositoryImpl @Inject constructor(
    private val searchScrapsDataSource: SearchScrapsDataSource,
) : SearchScrapsRepository {
    override suspend fun getSearchScrapsList(): Result<List<InternAnnouncementResponseModel>> =
        runCatching {
            searchScrapsDataSource.getSearchScraps().toSearchScrapsEntity()
        }
}