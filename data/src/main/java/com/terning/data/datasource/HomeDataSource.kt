package com.terning.data.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.NonDataBaseResponse
import com.terning.data.dto.request.ChangeFilterRequestDto
import com.terning.data.dto.response.HomeFilteringInfoResponseDto
import com.terning.data.dto.response.HomeRecommendInternResponseDto
import com.terning.data.dto.response.HomeUpcomingInternResponseDto

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