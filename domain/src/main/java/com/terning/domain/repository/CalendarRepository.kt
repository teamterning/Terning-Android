package com.terning.domain.repository

import com.terning.domain.entity.CalendarScrapDetail
import com.terning.domain.entity.CalendarScrap
import java.time.LocalDate

interface CalendarRepository{
    suspend fun getScrapMonth(year: Int, month: Int): Result<Map<String, List<CalendarScrap>>>
    suspend fun getScrapMonthList(year: Int, month: Int): Result<Map<String,List<CalendarScrapDetail>>>
    suspend fun getScrapDayList(currentDate: LocalDate): Result<List<CalendarScrapDetail>>
}