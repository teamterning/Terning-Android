package com.terning.core.type

import androidx.annotation.StringRes
import com.terning.core.R

enum class WorkingPeriod(
    val workingPeriod: String,
    @StringRes val text: Int,
) {
    SHORT("short", R.string.change_filter_period_1),
    MIDDLE("middle", R.string.change_filter_period_2),
    LONG("long", R.string.change_filter_period_3),
}