package com.terning.data.datasourceimpl

import com.terning.data.datasource.CalendarDataSource
import com.terning.data.dto.BaseResponse
import com.terning.data.dto.request.CalendarDayListRequestDto
import com.terning.data.dto.request.CalendarMonthListRequestDto
import com.terning.data.dto.request.CalendarMonthRequestDto
import com.terning.data.dto.response.CalendarDayListResponseDto
import com.terning.data.dto.response.CalendarMonthListResponseDto
import com.terning.data.dto.response.CalendarMonthResponseDto
import com.terning.data.service.CalendarService
import javax.inject.Inject

class CalendarDataSourceImpl @Inject constructor(
    private val calendarService: CalendarService
) : CalendarDataSource {
    override suspend fun getCalendarMonth(request: CalendarMonthRequestDto): BaseResponse<List<CalendarMonthResponseDto>> =
        calendarService.getCalendarScrapMonth(request.year, request.month)

    override suspend fun getCalendarMonthList(request: CalendarMonthListRequestDto): BaseResponse<List<CalendarMonthListResponseDto>> =
        calendarService.getCalendarScrapMonthList(request.year, request.month)

    override suspend fun getCalendarDayList(request: CalendarDayListRequestDto): BaseResponse<List<CalendarDayListResponseDto>> =
        calendarService.getCalendarScrapDayList(request.date)
}