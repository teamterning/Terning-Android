package com.terning.data.repositoryimpl

import com.terning.data.datasource.HomeDataSource
import com.terning.data.dto.request.toChangeFilterRequestDto
import com.terning.data.mapper.home.toHomeFilteringInfo
import com.terning.data.mapper.home.toHomeRecommendInternList
import com.terning.data.mapper.home.toHomeUpcomingInternList
import com.terning.domain.entity.home.HomeFilteringInfo
import com.terning.domain.entity.home.HomeRecommendIntern
import com.terning.domain.entity.home.HomeUpcomingIntern
import com.terning.domain.entity.request.ChangeFilteringRequestModel
import com.terning.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource,
) : HomeRepository {
    override suspend fun getHomeUpcomingInternList(): Result<List<HomeUpcomingIntern>> =
        runCatching {
            homeDataSource.getUpcomingIntern().result.map { homeTodayInternResponseDto ->
                homeTodayInternResponseDto.toHomeUpcomingInternList()
            }
        }

    override suspend fun getRecommendIntern(
        sortBy: String,
        startYear: Int,
        startMonth: Int
    ): Result<List<HomeRecommendIntern>> =
        runCatching {
            homeDataSource.getRecommendIntern(
                sortBy = sortBy,
                startYear = startYear,
                startMonth = startMonth
            ).result.map { homeRecommendInternResponseDto ->
                homeRecommendInternResponseDto.toHomeRecommendInternList()
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
}