package com.terning.data.datasourceimpl

import com.terning.data.datasource.HomeDataSource
import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.HomeRecommendInternResponseDto
import com.terning.data.dto.response.HomeTodayInternResponseDto
import com.terning.data.service.HomeService
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val homeService: HomeService,
) : HomeDataSource {
    override suspend fun getTodayIntern(): BaseResponse<List<HomeTodayInternResponseDto>> =
        homeService.getHomeTodayIntern()

    override suspend fun getRecommendIntern(
        sortBy: String,
        year: Int,
        month: Int
    ): BaseResponse<List<HomeRecommendInternResponseDto>> =
        homeService.getRecommendIntern(sortBy = sortBy, year = year, month = month)
}