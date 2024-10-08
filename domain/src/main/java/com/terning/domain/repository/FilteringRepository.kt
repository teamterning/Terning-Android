package com.terning.domain.repository

import com.terning.domain.entity.filtering.Filtering

interface FilteringRepository {
    suspend fun postFiltering(
        userId: Long,
        request: Filtering
    ): Result<Unit>
}