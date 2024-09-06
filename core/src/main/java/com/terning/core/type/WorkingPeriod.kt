package com.terning.core.type

import androidx.annotation.StringRes
import com.terning.core.R

enum class WorkingPeriod(
    val workingPeriod: String,
    @StringRes val text: Int,
) {
    FIRST("1 ~ 3개월", R.string.change_filter_period_1),
    SECOND("4 ~ 6개월", R.string.change_filter_period_2),
    THIRD("7개월 이상", R.string.change_filter_period_3),
}