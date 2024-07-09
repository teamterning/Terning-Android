package com.terning.core.extension

import java.time.LocalDate

fun LocalDate.getStringAsTitle(): String =
    "${year}년 ${monthValue.toString().padStart(2, '0')}월"