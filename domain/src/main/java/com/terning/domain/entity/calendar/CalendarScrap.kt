package com.terning.domain.entity.calendar

data class CalendarScrap(
    val scrapId: Long,
    val title: String,
    val deadLine: String,
    val color: String,
    val isScrapped: Boolean
)