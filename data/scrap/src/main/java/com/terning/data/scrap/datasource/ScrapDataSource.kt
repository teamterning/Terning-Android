package com.terning.data.scrap.datasource

import com.terning.core.network.NonDataBaseResponse
import com.terning.domain.scrap.entity.CalendarScrapRequest

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