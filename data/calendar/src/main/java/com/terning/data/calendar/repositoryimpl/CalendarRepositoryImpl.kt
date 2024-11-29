package com.terning.data.calendar.repositoryimpl

import com.terning.data.calendar.datasource.CalendarDataSource
import com.terning.data.calendar.dto.request.CalendarDayListRequestDto
import com.terning.data.calendar.dto.request.CalendarMonthListRequestDto
import com.terning.data.mapper.toCalendarScrapDetail
import com.terning.data.mapper.toCalendarScrapDetailList
import com.terning.data.mapper.toCalendarScrapList
import com.terning.domain.entity.calendar.CalendarScrapDetail
import com.terning.domain.entity.calendar.CalendarScrap
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
                request = com.terning.data.calendar.dto.request.CalendarMonthRequestDto(
                    year = year,
                    month = month
                )
            ).result

            val scrapModelMapByDeadLine = result.flatMap { dto ->
                dto.toCalendarScrapList()
            }.groupBy { it.deadLine }

            scrapModelMapByDeadLine
        }

    override suspend fun getScrapMonthList(
        year: Int,
        month: Int
    ): Result<Map<String, List<CalendarScrapDetail>>> =
        runCatching {
            val result = calendarDataSource.getCalendarMonthList(
                request = CalendarMonthListRequestDto(
                    year = year,
                    month = month
                )
            ).result

            val scrapModelMapByDeadLine = result.flatMap { dto ->
                dto.toCalendarScrapDetailList()
            }.groupBy { it.deadline }

            scrapModelMapByDeadLine
        }

    override suspend fun getScrapDayList(
        currentDate: LocalDate
    ): Result<List<CalendarScrapDetail>> =
        runCatching {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val request = CalendarDayListRequestDto(
                currentDate.format(formatter)
            )
            val response = calendarDataSource.getCalendarDayList(request)
            val scrapModelList = response.result.map { scrap ->
                scrap.toCalendarScrapDetail()
            }
            scrapModelList
        }
}
