package com.terning.domain.entity.response

data class CalendarScrapModel(
    val scrapId: Long,
    val title: String,
    val deadLine: String,
    val color: String,
    val isScrapped: Boolean
)