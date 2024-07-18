package com.terning.data.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.NonDataBaseResponse
import com.terning.data.dto.request.ChangeFilterRequestDto
import com.terning.data.dto.response.HomeFilteringInfoResponseDto
import com.terning.data.dto.response.HomeRecommendInternResponseDto
import com.terning.data.dto.response.HomeTodayInternResponseDto

interface HomeDataSource {
    suspend fun getTodayIntern(): BaseResponse<List<HomeTodayInternResponseDto>>

    suspend fun getRecommendIntern(
        sortBy: String,
        startYear: Int,
        startMonth: Int
    ): BaseResponse<List<HomeRecommendInternResponseDto>>

    suspend fun getFilteringInfo(): BaseResponse<HomeFilteringInfoResponseDto>

    suspend fun putFilteringInfo(changeFilterRequestDto: ChangeFilterRequestDto): NonDataBaseResponse
}