package com.terning.domain.home.repository

import com.terning.domain.home.entity.ChangeFilteringRequestModel
import com.terning.domain.home.entity.HomeFilteringInfo
import com.terning.domain.home.entity.HomeRecommendIntern
import com.terning.domain.home.entity.HomeUpcomingIntern

interface HomeRepository {
    suspend fun getHomeUpcomingInternList(): Result<HomeUpcomingIntern>

    suspend fun getRecommendIntern(
        sortBy: String,
        startYear: Int,
        startMonth: Int
    ): Result<HomeRecommendIntern>

    suspend fun getFilteringInfo(): Result<HomeFilteringInfo>

    suspend fun putFilteringInfo(
        putFilteringRequest: ChangeFilteringRequestModel,
    ): Result<Unit>
}