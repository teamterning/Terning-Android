package com.terning.data.scrap.repositoryimpl

import com.terning.domain.entity.calendar.CalendarScrapRequest
import com.terning.domain.repository.ScrapRepository
import javax.inject.Inject

class ScrapRepositoryImpl @Inject constructor(
    private val scrapDataSource: com.terning.data.scrap.datasource.ScrapDataSource,
) : ScrapRepository {
    override suspend fun postScrap(calendarScrapRequest: CalendarScrapRequest)
            : Result<Unit> = runCatching {
        scrapDataSource.postScrap(calendarScrapRequest)
    }

    override suspend fun deleteScrap(calendarScrapRequest: CalendarScrapRequest)
            : Result<Unit> = runCatching {
        scrapDataSource.deleteScrap(calendarScrapRequest)
    }

    override suspend fun patchScrap(calendarScrapRequest: CalendarScrapRequest)
            : Result<Unit>  = runCatching {
        scrapDataSource.patchScrap(calendarScrapRequest)
    }
}