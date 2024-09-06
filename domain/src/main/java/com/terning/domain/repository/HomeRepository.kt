package com.terning.domain.repository

import com.terning.domain.entity.home.HomeFilteringInfo
import com.terning.domain.entity.home.HomeRecommendIntern
import com.terning.domain.entity.home.HomeUpcomingIntern
import com.terning.domain.entity.request.ChangeFilteringRequestModel

interface HomeRepository {
    suspend fun getHomeUpcomingInternList(): Result<List<HomeUpcomingIntern>>

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