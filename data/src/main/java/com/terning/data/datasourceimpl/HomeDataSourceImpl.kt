package com.terning.data.datasourceimpl

import com.terning.data.datasource.HomeDataSource
import com.terning.data.dto.BaseResponse
import com.terning.data.dto.NonDataBaseResponse
import com.terning.data.dto.request.ChangeFilterRequestDto
import com.terning.data.dto.response.HomeFilteringInfoResponseDto
import com.terning.data.dto.response.HomeRecommendInternResponseDto
import com.terning.data.dto.response.HomeUpcomingInternResponseDto
import com.terning.data.service.HomeService
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val homeService: HomeService,
) : HomeDataSource {
    override suspend fun getUpcomingIntern(): BaseResponse<List<HomeUpcomingInternResponseDto>> =
        homeService.getHomeUpcomingIntern()

    override suspend fun getRecommendIntern(
        sortBy: String,
        startYear: Int,
        startMonth: Int
    ): BaseResponse<List<HomeRecommendInternResponseDto>> =
        homeService.getRecommendIntern(
            sortBy = sortBy,
            startYear = startYear,
            startMonth = startMonth
        )

    override suspend fun getFilteringInfo(): BaseResponse<HomeFilteringInfoResponseDto> =
        homeService.getFilteringInfo()

    override suspend fun putFilteringInfo(request: ChangeFilterRequestDto): NonDataBaseResponse =
        homeService.putFilteringInfo(request)
}