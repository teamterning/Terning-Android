package com.terning.data.home.repositoryimpl

import com.terning.data.home.dto.request.toChangeFilterRequestDto
import com.terning.data.home.mapper.toHomeFilteringInfo
import com.terning.data.home.mapper.toHomeRecommendInternList
import com.terning.data.home.mapper.toHomeUpcomingInternList
import com.terning.domain.home.entity.ChangeFilteringRequestModel
import com.terning.domain.home.entity.HomeFilteringInfo
import com.terning.domain.home.entity.HomeRecommendIntern
import com.terning.domain.home.entity.HomeUpcomingIntern
import com.terning.domain.home.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: com.terning.data.home.datasource.HomeDataSource,
) : HomeRepository {
    override suspend fun getHomeUpcomingInternList(): Result<HomeUpcomingIntern> =
        runCatching {
            homeDataSource.getUpcomingIntern().result.toHomeUpcomingInternList()
        }

    override suspend fun getRecommendIntern(
        sortBy: String,
        startYear: Int,
        startMonth: Int
    ): Result<HomeRecommendIntern> =
        runCatching {
            homeDataSource.getRecommendIntern(
                sortBy = sortBy,
                startYear = startYear,
                startMonth = startMonth
            ).result.toHomeRecommendInternList()
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
}