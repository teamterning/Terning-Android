package com.terning.feature.calendar.models

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class Scrap(
    val text: String,
    val backgroundColor: Color,
    val dDay: String,
    val period: String,
    val isScraped: Boolean = true,
    val image: String? = null
)
