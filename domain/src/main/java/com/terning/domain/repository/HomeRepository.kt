package com.terning.domain.repository

import com.terning.domain.entity.response.HomeRecommendInternModel

interface HomeRepository {
    suspend fun getRecommendIntern(
        sortBy: String,
        year: Int,
        month: Int
    ): Result<List<HomeRecommendInternModel>>
}