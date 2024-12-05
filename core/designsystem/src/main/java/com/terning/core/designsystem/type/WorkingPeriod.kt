package com.terning.core.designsystem.type

import androidx.annotation.StringRes
import com.terning.core.designsystem.R

enum class WorkingPeriod(
    val stringValue: String,
    @StringRes val stringResId: Int,
) {
    SHORT("short", R.string.change_filter_period_1),
    MIDDLE("middle", R.string.change_filter_period_2),
    LONG("long", R.string.change_filter_period_3);

    companion object {
        fun fromString(value: String?): WorkingPeriod = when (value) {
            "short" -> SHORT
            "middle" -> MIDDLE
            "long" -> LONG
            else -> SHORT
        }
    }
}