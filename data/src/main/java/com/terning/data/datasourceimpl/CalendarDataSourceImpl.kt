package com.terning.data.datasourceimpl

import com.terning.data.datasource.CalendarDataSource
import com.terning.data.dto.BaseResponse
import com.terning.data.dto.request.CalendarDayListRequestDto
import com.terning.data.dto.request.CalendarMonthListRequestDto
import com.terning.data.dto.request.CalendarMonthRequestDto
import com.terning.data.dto.response.CalendarListResponseDto
import com.terning.data.dto.response.CalendarMonthResponseDto
import com.terning.data.service.CalendarService
import javax.inject.Inject

class CalendarDataSourceImpl @Inject constructor(
    private val calendarService: CalendarService
) : CalendarDataSource {
    override suspend fun getCalendarMonth(request: CalendarMonthRequestDto): BaseResponse<List<CalendarMonthResponseDto>> =
        CalendarList.getCalendarScrapMonth(request)

    override suspend fun getCalendarMonthList(request: CalendarMonthListRequestDto): BaseResponse<List<CalendarListResponseDto>> =
        CalendarList.getCalendarScrapMonthList(request)

    override suspend fun getCalendarDayList(request: CalendarDayListRequestDto): BaseResponse<List<CalendarListResponseDto>> =
        CalendarList.getCalendarScrapDayList(request)
}