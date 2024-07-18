package com.terning.data.repositoryimpl

import com.terning.data.datasource.HomeDataSource
import com.terning.data.dto.request.toChangeFilterRequestDto
import com.terning.domain.entity.request.ChangeFilteringRequestModel
import com.terning.domain.entity.response.HomeFilteringInfoModel
import com.terning.domain.entity.response.HomeRecommendInternModel
import com.terning.domain.entity.response.HomeTodayInternModel
import com.terning.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource,
) : HomeRepository {
    override suspend fun getHomeTodayInternList(): Result<List<HomeTodayInternModel>> =
        runCatching {
            homeDataSource.getTodayIntern().result.map {
                it.toHomeTodayInternList()
            }
        }

    override suspend fun getRecommendIntern(
        sortBy: String,
        startYear: Int,
        startMonth: Int
    ): Result<List<HomeRecommendInternModel>> =
        runCatching {
            homeDataSource.getRecommendIntern(
                sortBy = sortBy,
                startYear = startYear,
                startMonth = startMonth
            ).result.map {
                it.toRecommendInternEntity()
            }
        }

    override suspend fun getFilteringInfo(): Result<HomeFilteringInfoModel> =
        runCatching {
            homeDataSource.getFilteringInfo().result.toHomeFilteringInfoModel()
        }

    override suspend fun putFilteringInfo(putFilteringRequest: ChangeFilteringRequestModel): Result<Unit> =
        runCatching {
            homeDataSource.putFilteringInfo(
                putFilteringRequest.toChangeFilterRequestDto()
            )
        }
}