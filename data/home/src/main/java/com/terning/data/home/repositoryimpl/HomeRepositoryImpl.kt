package com.terning.data.home.repositoryimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.terning.data.home.datasource.HomeDataSource
import com.terning.data.home.dto.request.toChangeFilterRequestDto
import com.terning.data.home.mapper.toHomeFilteringInfo
import com.terning.data.home.mapper.toHomeRecommendedIntern
import com.terning.data.home.mapper.toHomeUpcomingInternList
import com.terning.data.home.mapper.toRequestDto
import com.terning.data.home.pagingsource.HomePagingSource
import com.terning.domain.home.entity.ChangeFilteringRequestModel
import com.terning.domain.home.entity.FcmToken
import com.terning.domain.home.entity.HomeFilteringInfo
import com.terning.domain.home.entity.HomeRecommendedIntern
import com.terning.domain.home.entity.HomeUpcomingIntern
import com.terning.domain.home.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource,
) : HomeRepository {

    override suspend fun getHomeUpcomingInternList(): Result<HomeUpcomingIntern> =
        runCatching {
            homeDataSource.getUpcomingIntern().result.toHomeUpcomingInternList()
        }

    override fun getRecommendIntern(sortBy: String): Flow<PagingData<HomeRecommendedIntern>> {
        return Pager(
            PagingConfig(pageSize = 10)
        ) {
            HomePagingSource(
                sortBy = sortBy,
                dataSource = homeDataSource
            )
        }.flow.map { pagedData ->
            pagedData.map { it.second.toHomeRecommendedIntern(it.first) }
        }
    }

    override suspend fun getFilteringInfo(): Result<HomeFilteringInfo> =
        runCatching {
            homeDataSource.getFilteringInfo().result.toHomeFilteringInfo()
        }

    override suspend fun putFilteringInfo(putFilteringRequest: ChangeFilteringRequestModel): Result<Unit> =
        runCatching {
            homeDataSource.putFilteringInfo(
                putFilteringRequest.toChangeFilterRequestDto()
            )
        }

    override suspend fun sendFcmToken(fcmTokenRequest: FcmToken): Result<Unit> =
        runCatching {
            homeDataSource.sendFcmToken(fcmTokenRequest.toRequestDto())
        }
}