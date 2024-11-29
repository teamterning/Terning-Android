package com.terning.data.filtering.repositoryimpl

import com.terning.data.filtering.mapper.toFilteringRequestDto
import com.terning.domain.entity.filtering.Filtering
import com.terning.domain.repository.FilteringRepository
import javax.inject.Inject

class FilteringRepositoryImpl @Inject constructor(
    private val filteringDataSource: com.terning.data.filtering.datasource.FilteringDataSource
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