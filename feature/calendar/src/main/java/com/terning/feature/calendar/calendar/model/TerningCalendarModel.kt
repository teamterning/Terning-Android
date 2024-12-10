package com.terning.feature.calendar.calendar.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import java.time.LocalDate
import java.time.YearMonth

/**
 * 터닝의 달력 로직을 관리하는 클래스입니다.
 * 달력의 시작과 끝 연도를 설정하여 달력의 크기를 지정합니다.
 *
 * @param startYear  캘린더의 시작 연도
 * @param endYear  캘린더의 끝 연도
 */

@Immutable
class TerningCalendarModel (
    private val startYear: Int = DEFAULT_START_YEAR,
    private val endYear: Int = DEFAULT_END_YEAR
) {
    private val currentDate = LocalDate.now()
    private val startYearDate = LocalDate.of(startYear, 1, 1)
    private val endYearDate = LocalDate.of(endYear, 12, 31)

    val pageCount = (endYearDate.year - startYearDate.year) * 12
    val initialPage = (currentDate.year - startYearDate.year) * 12 + currentDate.monthValue - 1

    /**
     * 페이지 값을 사용하여 [MonthModel]을 반환합니다.
     */
    fun getMonthModelByPage(page: Int): MonthModel {
        val localDate = getLocalDateByPage(page)
        return getMonthModelByPage(localDate = localDate)
    }

    /**
     * [LocalDate]를 사용하여 [MonthModel]을 반환합니다.
     */
    fun getMonthModelByPage(localDate: LocalDate): MonthModel {
        val yearMonth = YearMonth.of(localDate.year, localDate.monthValue)
        return getMonthModelByPage(yearMonth = yearMonth)
    }

    /**
     * [YearMonth]를 사용하여 [MonthModel]을 반환합니다.
     */
    fun getMonthModelByPage(yearMonth: YearMonth): MonthModel {
        return MonthModel(yearMonth = yearMonth)
    }

    /**
     * 현재 페이지의 연도, 월, 1일을 반환합니다.
     * @return [LocalDate]
     */
    fun getLocalDateByPage(page: Int): LocalDate = LocalDate.of(
        startYear + page / 12,
        page % 12 + 1,
        1
    )

    /**
     * 현재 페이지의 연도, 월을 반환합니다.
     * @return [YearMonth]
     */
    fun getYearMonthByPage(page: Int): YearMonth = YearMonth.of(
        startYear + page / 12,
        page % 12 + 1,
    )

    companion object {
        private const val DEFAULT_START_YEAR = 2020
        private const val DEFAULT_END_YEAR = 2030

        val LocalCalendarModel = staticCompositionLocalOf<TerningCalendarModel> {
            error("No CalendarModel provided")
        }
    }
}