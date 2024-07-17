package com.terning.data.service

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.CalendarMonthListResponseDto
import com.terning.data.dto.response.CalendarMonthResponseDto
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
        @Query("year") year: Int,
        @Query("month") month: Int,
        @Query("day") day: Int
    ): BaseResponse<List<CalendarMonthListResponseDto>>
}