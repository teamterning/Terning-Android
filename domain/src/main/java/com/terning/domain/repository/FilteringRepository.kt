package com.terning.domain.repository

import com.terning.domain.entity.request.FilteringRequestModel

interface FilteringRepository {
    suspend fun postFiltering(
        userId: Long,
        request: FilteringRequestModel
    ): Result<Unit>
}