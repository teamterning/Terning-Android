package com.terning.domain.repository

import com.terning.domain.entity.response.HomeFilteringInfoModel
import com.terning.domain.entity.response.HomeRecommendInternModel
import com.terning.domain.entity.response.HomeTodayInternModel

interface HomeRepository {
    suspend fun getHomeTodayInternList(): Result<List<HomeTodayInternModel>>

    suspend fun getRecommendIntern(
        sortBy: String,
        startYear: Int,
        startMonth: Int
    ): Result<List<HomeRecommendInternModel>>

    suspend fun getFilteringInfo(): Result<HomeFilteringInfoModel>
}