package com.terning.data.repositoryimpl

import com.terning.data.datasource.CalendarDataSource
import com.terning.data.dto.request.CalendarDayListRequestDto
import com.terning.data.dto.request.CalendarMonthListRequestDto
import com.terning.data.dto.request.CalendarMonthRequestDto
import com.terning.domain.entity.CalendarScrapDetailModel
import com.terning.domain.entity.CalendarScrap
import com.terning.domain.repository.CalendarRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class CalendarRepositoryImpl @Inject constructor(
    private val calendarDataSource: CalendarDataSource
) : CalendarRepository {
    override suspend fun getScrapMonth(
        year: Int,
        month: Int
    ): Result<Map<String, List<CalendarScrap>>> =
        runCatching {
            val result = calendarDataSource.getCalendarMonth(
                request = CalendarMonthRequestDto(
                    year = year,
                    month = month
                )
            ).result

            val scrapModelMapByDeadLine = result.flatMap { dto ->
                dto.toScrapModelList()
            }.groupBy { it.deadLine }

            scrapModelMapByDeadLine
        }

    override suspend fun getScrapMonthList(
        year: Int,
        month: Int
    ): Result<Map<String, List<CalendarScrapDetailModel>>> =
        runCatching {
            val result = calendarDataSource.getCalendarMonthList(
                request = CalendarMonthListRequestDto(
                    year = year,
                    month = month
                )
            ).result

            val scrapModelMapByDeadLine = result.flatMap { dto ->
                dto.toScrapDetailModelList()
            }.groupBy { it.deadLine }

            scrapModelMapByDeadLine
        }

    override suspend fun getScrapDayList(
        currentDate: LocalDate
    ): Result<List<CalendarScrapDetailModel>> =
        runCatching {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val request = CalendarDayListRequestDto(currentDate.format(formatter))
            val response = calendarDataSource.getCalendarDayList(request)
            val scrapModelList = response.result.map { scrap ->
                scrap.toScrapDetailModelList()
            }
            scrapModelList
        }
}
