package com.terning.domain.repository

import com.terning.domain.entity.response.CalendarScrapDetailModel
import com.terning.domain.entity.response.CalendarScrapModel

interface CalendarRepository{
    suspend fun getScrapMonth(year: Int, month: Int): Result<Map<String, List<CalendarScrapModel>>>
    suspend fun getScrapMonthList(year: Int, month: Int): Result<Map<String,List<CalendarScrapDetailModel>>>
    suspend fun getScrapDayList(year: Int, month: Int, day: Int): Result<List<CalendarScrapDetailModel>>
}