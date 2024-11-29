package com.terning.data.calendar.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.request.CalendarDayListRequestDto
import com.terning.data.dto.request.CalendarMonthListRequestDto
import com.terning.data.dto.request.CalendarMonthRequestDto
import com.terning.data.dto.response.CalendarDayListResponseDto
import com.terning.data.dto.response.CalendarMonthListResponseDto
import com.terning.data.dto.response.CalendarMonthResponseDto

interface CalendarDataSource {
    suspend fun getCalendarMonth(request: CalendarMonthRequestDto): BaseResponse<List<CalendarMonthResponseDto>>
    suspend fun getCalendarMonthList(request: CalendarMonthListRequestDto): BaseResponse<List<CalendarMonthListResponseDto>>
    suspend fun getCalendarDayList(request: CalendarDayListRequestDto): BaseResponse<List<CalendarDayListResponseDto>>
}