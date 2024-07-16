package com.terning.domain.repository

import com.terning.domain.entity.response.CalendarScrapModel

interface CalendarRepository{
    suspend fun getScrapMonth(year: Int, month: Int): Result<Map<String, List<CalendarScrapModel>>>
}