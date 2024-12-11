package com.terning.feature.calendar.calendar.model

import androidx.compose.runtime.Immutable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * 달력의 하루를 나타내는 데이터클래스 모델
 *
 * @param date 날짜
 * @param weekIndex 속한 주의 인덱스
 * @param isOutDate 이번 월에 포함되지 않는 날인지에 대한 불리언 값
 */

@Immutable
data class DayModel(
    val date: LocalDate = LocalDate.now(),
    val weekIndex: Int = 0,
    val isOutDate: Boolean = false
) {
    fun isToday(): Boolean = date == LocalDate.now()

    fun getScrapMapKey(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return date.format(formatter)
    }
}