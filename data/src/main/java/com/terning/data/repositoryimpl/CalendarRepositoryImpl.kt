package com.terning.data.repositoryimpl

import com.terning.data.datasource.CalendarDataSource
import com.terning.data.dto.request.CalendarMonthRequestDto
import com.terning.domain.entity.response.CalendarScrapModel
import com.terning.domain.repository.CalendarRepository
import javax.inject.Inject

class CalendarRepositoryImpl @Inject constructor(
    private val calendarDataSource: CalendarDataSource
): CalendarRepository {
    override suspend fun getScrapMonth(year: Int, month: Int): Result<Map<String, List<CalendarScrapModel>>> =
        runCatching {
            val result = calendarDataSource.getScrapMonth(
                    request = CalendarMonthRequestDto(
                        year = year,
                        month = month
                    )
            ).result

            val scrapModelMapByDeadLine = result.flatMap{ dto ->
                dto.toScrapModelList()
            }.groupBy { it.deadLine }

            scrapModelMapByDeadLine
        }
}