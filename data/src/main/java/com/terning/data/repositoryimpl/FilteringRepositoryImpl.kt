package com.terning.data.repositoryimpl

import com.terning.data.datasource.FilteringDataSource
import com.terning.data.mapper.filtering.toFilteringRequestDto
import com.terning.domain.entity.filtering.Filtering
import com.terning.domain.repository.FilteringRepository
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