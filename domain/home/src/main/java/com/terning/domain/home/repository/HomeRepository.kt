package com.terning.domain.home.repository

import androidx.paging.PagingData
import com.terning.domain.home.entity.ChangeFilteringRequestModel
import com.terning.domain.home.entity.FcmToken
import com.terning.domain.home.entity.HomeFilteringInfo
import com.terning.domain.home.entity.HomeRecommendedIntern
import com.terning.domain.home.entity.HomeUpcomingIntern
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getHomeUpcomingInternList(): Result<HomeUpcomingIntern>

    fun getRecommendIntern(
        sortBy: String
    ): Flow<PagingData<HomeRecommendedIntern>>

    suspend fun getFilteringInfo(): Result<HomeFilteringInfo>

    suspend fun putFilteringInfo(
        putFilteringRequest: ChangeFilteringRequestModel,
    ): Result<Unit>

    suspend fun sendFcmToken(fcmTokenRequest: FcmToken): Result<Unit>
}