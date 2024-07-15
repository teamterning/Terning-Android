package com.terning.domain.repository

import com.terning.domain.entity.response.HomeRecommendInternModel

interface HomeRepository {
    suspend fun getRecommendIntern(sortBy: String): Result<List<HomeRecommendInternModel>>
}