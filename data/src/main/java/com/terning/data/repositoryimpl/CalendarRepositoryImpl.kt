package com.terning.data.repositoryimpl

import com.terning.data.datasource.CalendarDataSource
import com.terning.data.dto.request.CalendarDayListRequestDto
import com.terning.data.dto.request.CalendarMonthListRequestDto
import com.terning.data.dto.request.CalendarMonthRequestDto
import com.terning.domain.entity.response.CalendarScrapDetailModel
import com.terning.domain.entity.response.CalendarScrapModel
import com.terning.domain.repository.CalendarRepository
import javax.inject.Inject

class CalendarRepositoryImpl @Inject constructor(
    private val calendarDataSource: CalendarDataSource
) : CalendarRepository {
    override suspend fun getScrapMonth(
        year: Int,
        month: Int
    ): Result<Map<String, List<CalendarScrapModel>>> =
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
        year: Int,
        month: Int,
        day: Int
    ): Result<List<CalendarScrapDetailModel>> =
        runCatching {
            val monthString = month.toString().padStart(2, '0')
            val request = CalendarDayListRequestDto("$year-$monthString-$day")
            val response = calendarDataSource.getCalendarDayList(request)
            val scrapModelList = response.result.map { scrap ->
                scrap.toScrapDetailModelList()
            }
            scrapModelList
        }


}
