package com.terning.domain.repository

import com.terning.domain.entity.HomeFilteringInfo
import com.terning.domain.entity.HomeRecommendIntern
import com.terning.domain.entity.HomeTodayInternModel
import com.terning.domain.entity.request.ChangeFilteringRequestModel

interface HomeRepository {
    suspend fun getHomeTodayInternList(): Result<List<HomeTodayInternModel>>

    suspend fun getRecommendIntern(
        sortBy: String,
        startYear: Int,
        startMonth: Int
    ): Result<List<HomeRecommendIntern>>

    suspend fun getFilteringInfo(): Result<HomeFilteringInfo>

    suspend fun putFilteringInfo(
        putFilteringRequest: ChangeFilteringRequestModel,
    ): Result<Unit>
}