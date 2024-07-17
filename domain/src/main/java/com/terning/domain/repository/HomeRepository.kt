package com.terning.domain.repository

import com.terning.domain.entity.response.HomeRecommendInternModel
import com.terning.domain.entity.response.HomeTodayInternModel

interface HomeRepository {
    suspend fun getHomeTodayInternList(): Result<List<HomeTodayInternModel>>

    suspend fun getRecommendIntern(
        sortBy: String,
        year: Int,
        month: Int
    ): Result<List<HomeRecommendInternModel>>
}