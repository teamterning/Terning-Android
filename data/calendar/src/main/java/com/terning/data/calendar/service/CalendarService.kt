package com.terning.data.calendar.service

import com.terning.core.network.BaseResponse
import com.terning.data.calendar.dto.response.CalendarDayListResponseDto
import com.terning.data.calendar.dto.response.CalendarMonthListResponseDto
import com.terning.data.calendar.dto.response.CalendarMonthResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CalendarService{
    @GET("/api/v1/calendar/monthly-default")
    suspend fun getCalendarScrapMonth(
        @Query("year") year: Int,
        @Query("month") month: Int
    ): BaseResponse<List<CalendarMonthResponseDto>>

    @GET("/api/v1/calendar/monthly-list")
    suspend fun getCalendarScrapMonthList(
        @Query("year") year: Int,
        @Query("month") month: Int
    ): BaseResponse<List<CalendarMonthListResponseDto>>

    @GET("/api/v1/calendar/daily")
    suspend fun getCalendarScrapDayList(
        @Query("date") date: String
    ): BaseResponse<List<CalendarDayListResponseDto>>
}