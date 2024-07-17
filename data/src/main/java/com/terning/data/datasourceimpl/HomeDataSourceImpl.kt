package com.terning.data.datasourceimpl

import com.terning.data.datasource.HomeDataSource
import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.HomeTodayInternResponseDto
import com.terning.data.service.HomeService
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val homeService: HomeService,
) : HomeDataSource {
    override suspend fun getTodayIntern(): BaseResponse<List<HomeTodayInternResponseDto>> =
        homeService.getHomeTodayIntern()
}