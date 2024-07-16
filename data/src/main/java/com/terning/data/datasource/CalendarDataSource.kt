package com.terning.data.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.request.CalendarDayListRequestDto
import com.terning.data.dto.request.CalendarMonthListRequestDto
import com.terning.data.dto.request.CalendarMonthRequestDto
import com.terning.data.dto.response.CalendarListResponseDto
import com.terning.data.dto.response.CalendarMonthResponseDto

interface CalendarDataSource {
    suspend fun getCalendarMonth(request: CalendarMonthRequestDto): BaseResponse<List<CalendarMonthResponseDto>>
    suspend fun getCalendarMonthList(request: CalendarMonthListRequestDto): BaseResponse<List<CalendarListResponseDto>>
    suspend fun getCalendarDayList(request: CalendarDayListRequestDto): BaseResponse<List<CalendarListResponseDto>>
}