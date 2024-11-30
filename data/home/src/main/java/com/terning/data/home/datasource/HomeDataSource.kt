package com.terning.data.home.datasource

import com.terning.core.network.BaseResponse
import com.terning.core.network.NonDataBaseResponse
import com.terning.data.home.dto.request.ChangeFilterRequestDto
import com.terning.data.home.dto.response.HomeFilteringInfoResponseDto
import com.terning.data.home.dto.response.HomeRecommendInternResponseDto
import com.terning.data.home.dto.response.HomeUpcomingInternResponseDto

interface HomeDataSource {
    suspend fun getUpcomingIntern(): BaseResponse<HomeUpcomingInternResponseDto>

    suspend fun getRecommendIntern(
        sortBy: String,
        startYear: Int,
        startMonth: Int
    ): BaseResponse<HomeRecommendInternResponseDto>

    suspend fun getFilteringInfo(): BaseResponse<HomeFilteringInfoResponseDto>

    suspend fun putFilteringInfo(changeFilterRequestDto: ChangeFilterRequestDto): NonDataBaseResponse
}