package com.terning.domain.repository

import com.terning.domain.entity.response.RecommendInternModel

interface InternRepository {
    suspend fun getRecommendIntern(sortBy: String): Result<List<RecommendInternModel>>
}