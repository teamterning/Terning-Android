package com.terning.core.extension

import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

fun LocalDate.getStringAsTitle(): String =
    "${year}년 ${monthValue.toString().padStart(2, '0')}월"

fun LocalDate.getDateStringInKorean(): String =
    "${monthValue}월 ${dayOfMonth}일 ${dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN)}"

fun LocalDate.getWeekIndexContainingSelectedDate(): Int = dayOfMonth / 7

fun LocalDate.isToday(): Boolean = this == LocalDate.now()