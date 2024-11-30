package com.terning.domain.filtering.repository

import com.terning.domain.filtering.entity.Filtering

interface FilteringRepository {
    suspend fun postFiltering(
        userId: Long,
        request: Filtering
    ): Result<Unit>
}