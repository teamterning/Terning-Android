package com.terning.data.filtering.repositoryimpl

import com.terning.data.filtering.datasource.FilteringDataSource
import com.terning.data.filtering.mapper.toFilteringRequestDto
import com.terning.domain.filtering.entity.Filtering
import com.terning.domain.filtering.repository.FilteringRepository
import javax.inject.Inject

class FilteringRepositoryImpl @Inject constructor(
    private val filteringDataSource: FilteringDataSource
) : FilteringRepository {
    override suspend fun postFiltering(
        userId: Long,
        request: Filtering
    ): Result<Unit> =
        runCatching {
            filteringDataSource.postFiltering(
                userId,
                request.toFilteringRequestDto()
            )
        }
}