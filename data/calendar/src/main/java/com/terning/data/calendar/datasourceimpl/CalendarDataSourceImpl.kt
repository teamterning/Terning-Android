package com.terning.data.calendar.datasourceimpl

import com.terning.core.network.BaseResponse
import com.terning.data.calendar.dto.request.CalendarDayListRequestDto
import com.terning.data.calendar.dto.request.CalendarMonthListRequestDto
import com.terning.data.calendar.dto.request.CalendarMonthRequestDto
import com.terning.data.calendar.dto.response.CalendarDayListResponseDto
import com.terning.data.calendar.dto.response.CalendarMonthListResponseDto
import com.terning.data.calendar.dto.response.CalendarMonthResponseDto
import com.terning.data.calendar.service.CalendarService
import javax.inject.Inject

class CalendarDataSourceImpl @Inject constructor(
    private val calendarService: CalendarService
) : com.terning.data.calendar.datasource.CalendarDataSource {
    override suspend fun getCalendarMonth(request: CalendarMonthRequestDto): BaseResponse<List<CalendarMonthResponseDto>> =
        calendarService.getCalendarScrapMonth(request.year, request.month)

    override suspend fun getCalendarMonthList(request: CalendarMonthListRequestDto): BaseResponse<List<CalendarMonthListResponseDto>> =
        calendarService.getCalendarScrapMonthList(request.year, request.month)

    override suspend fun getCalendarDayList(request: CalendarDayListRequestDto): BaseResponse<List<CalendarDayListResponseDto>> =
        calendarService.getCalendarScrapDayList(request.date)
}