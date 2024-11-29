package com.terning.domain.calendar.repository

import com.terning.domain.entity.calendar.CalendarScrapDetail
import com.terning.domain.entity.calendar.CalendarScrap
import java.time.LocalDate

interface CalendarRepository{
    suspend fun getScrapMonth(year: Int, month: Int): Result<Map<String, List<CalendarScrap>>>
    suspend fun getScrapMonthList(year: Int, month: Int): Result<Map<String,List<CalendarScrapDetail>>>
    suspend fun getScrapDayList(currentDate: LocalDate): Result<List<CalendarScrapDetail>>
}