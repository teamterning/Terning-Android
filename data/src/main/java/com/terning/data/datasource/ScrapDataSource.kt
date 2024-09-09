package com.terning.data.datasource

import com.terning.data.dto.NonDataBaseResponse
import com.terning.domain.entity.calendar.CalendarScrapRequest

interface ScrapDataSource {
    suspend fun postScrap(
        calendarScrapRequest: CalendarScrapRequest,
    ): NonDataBaseResponse

    suspend fun deleteScrap(
        calendarScrapRequest: CalendarScrapRequest,
    ): NonDataBaseResponse

    suspend fun patchScrap(
        calendarScrapRequest: CalendarScrapRequest,
    ): NonDataBaseResponse
}