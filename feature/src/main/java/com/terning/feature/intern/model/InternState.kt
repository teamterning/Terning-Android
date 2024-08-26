package com.terning.feature.intern.model

import androidx.compose.ui.graphics.Color
import com.terning.core.designsystem.theme.CalRed

data class InternState(
    val dDay: String = "",
    val title: String = "",
    val deadline: String = "",
    val workingPeriod: String = "",
    val startDate: String = "",
    val scrapCount: Int = 0,
    val viewCount: Int = 0,
    val company: String = "",
    val companyCategory: String = "",
    val companyImage: String = "",
    val qualification: String = "",
    val jobType: String = "",
    val detail: String = "",
    val url: String = "",
    val scrapId: Long? = null,
    val isColorChange: Boolean = false,
    val isPaletteOpen: Boolean = false,
    val selectedColor: Color = CalRed,
    val isScrapDialogVisible: Boolean = false,
    val isScrappedState: Boolean = false,
    val showWeb: Boolean = false,
)