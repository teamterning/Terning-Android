package com.terning.data.datasourceimpl

import com.terning.data.datasource.ScrapDataSource
import com.terning.data.dto.NonDataBaseResponse
import com.terning.data.dto.request.ScrapColorRequestDto
import com.terning.data.service.ScrapService
import com.terning.domain.entity.calendar.CalendarScrapRequest
import javax.inject.Inject

class ScrapDataSourceImpl @Inject constructor(
    private val scrapService: ScrapService,
) : ScrapDataSource {
    override suspend fun postScrap(calendarScrapRequest: CalendarScrapRequest): NonDataBaseResponse =
        scrapService.postScrap(
            calendarScrapRequest.id,
            ScrapColorRequestDto(calendarScrapRequest.color)
        )

    override suspend fun deleteScrap(calendarScrapRequest: CalendarScrapRequest): NonDataBaseResponse =
        scrapService.deleteScrap(calendarScrapRequest.id)

    override suspend fun patchScrap(calendarScrapRequest: CalendarScrapRequest): NonDataBaseResponse =
        scrapService.patchScrap(
            calendarScrapRequest.id,
            ScrapColorRequestDto(calendarScrapRequest.color)
        )
}