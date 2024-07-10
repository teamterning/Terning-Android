package com.terning.core.extension

import java.time.LocalDate

fun LocalDate.getStringAsTitle(): String =
    "${year}년 ${monthValue.toString().padStart(2, '0')}월"

fun LocalDate.getWeekIndexContainingSelectedDate(): Int = dayOfMonth / 7

fun LocalDate.isToday(): Boolean = this == LocalDate.now()