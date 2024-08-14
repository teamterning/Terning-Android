package com.terning.domain.repository

import com.terning.domain.entity.HomeFilteringInfoModel
import com.terning.domain.entity.HomeRecommendInternModel
import com.terning.domain.entity.HomeTodayInternModel
import com.terning.domain.entity.request.ChangeFilteringRequestModel

interface HomeRepository {
    suspend fun getHomeTodayInternList(): Result<List<HomeTodayInternModel>>

    suspend fun getRecommendIntern(
        sortBy: String,
        startYear: Int,
        startMonth: Int
    ): Result<List<HomeRecommendInternModel>>

    suspend fun getFilteringInfo(): Result<HomeFilteringInfoModel>

    suspend fun putFilteringInfo(
        putFilteringRequest: ChangeFilteringRequestModel,
    ): Result<Unit>
}