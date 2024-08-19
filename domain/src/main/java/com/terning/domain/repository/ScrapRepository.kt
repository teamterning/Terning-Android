package com.terning.domain.repository

import com.terning.domain.entity.CalendarScrapRequest

interface ScrapRepository {
    suspend fun postScrap(calendarScrapRequest: CalendarScrapRequest): Result<Unit>
    suspend fun deleteScrap(calendarScrapRequest: CalendarScrapRequest): Result<Unit>
    suspend fun patchScrap(calendarScrapRequest: CalendarScrapRequest): Result<Unit>
}