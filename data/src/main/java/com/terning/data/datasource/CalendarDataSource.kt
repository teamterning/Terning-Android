package com.terning.data.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.request.CalendarMonthListRequestDto
import com.terning.data.dto.request.CalendarMonthRequestDto
import com.terning.data.dto.response.CalendarListResponseDto
import com.terning.data.dto.response.CalendarMonthResponseDto

interface CalendarDataSource {
    suspend fun getScrapMonth(request: CalendarMonthRequestDto): BaseResponse<List<CalendarMonthResponseDto>>
    suspend fun getScrapMonthList(request: CalendarMonthListRequestDto): BaseResponse<List<CalendarListResponseDto>>
}