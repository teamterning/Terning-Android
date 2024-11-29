package com.terning.domain.scrap.repository

import com.terning.domain.entity.calendar.CalendarScrapRequest

interface ScrapRepository {
    suspend fun postScrap(calendarScrapRequest: CalendarScrapRequest): Result<Unit>
    suspend fun deleteScrap(calendarScrapRequest: CalendarScrapRequest): Result<Unit>
    suspend fun patchScrap(calendarScrapRequest: CalendarScrapRequest): Result<Unit>
}