package com.terning.data.home.datasourceimpl

import com.terning.core.network.BaseResponse
import com.terning.core.network.NonDataBaseResponse
import com.terning.data.home.dto.request.ChangeFilterRequestDto
import com.terning.data.home.dto.response.HomeFilteringInfoResponseDto
import com.terning.data.home.dto.response.HomeRecommendInternResponseDto
import com.terning.data.home.dto.response.HomeUpcomingInternResponseDto
import com.terning.data.home.service.HomeService
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val homeService: HomeService,
) : com.terning.data.home.datasource.HomeDataSource {
    override suspend fun getUpcomingIntern(): BaseResponse<HomeUpcomingInternResponseDto> =
        homeService.getHomeUpcomingIntern()

    override suspend fun getRecommendIntern(
        sortBy: String,
        startYear: Int,
        startMonth: Int
    ): BaseResponse<HomeRecommendInternResponseDto> =
        homeService.getRecommendIntern(
            sortBy = sortBy,
            startYear = startYear,
            startMonth = startMonth
        )

    override suspend fun getFilteringInfo(): BaseResponse<HomeFilteringInfoResponseDto> =
        homeService.getFilteringInfo()

    override suspend fun putFilteringInfo(changeFilterRequestDto: ChangeFilterRequestDto): NonDataBaseResponse =
        homeService.putFilteringInfo(changeFilterRequestDto)
}