package com.terning.data.scrap.datasourceimpl

import com.terning.core.network.NonDataBaseResponse
import com.terning.data.scrap.datasource.ScrapDataSource
import com.terning.data.scrap.dto.request.ScrapColorRequestDto
import com.terning.data.scrap.service.ScrapService
import com.terning.domain.scrap.entity.CalendarScrapRequest
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