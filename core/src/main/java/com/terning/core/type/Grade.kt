package com.terning.core.type

import androidx.annotation.StringRes
import com.terning.core.R

enum class Grade(
    val grade: String,
    @StringRes val text: Int,
) {
    FRESHMAN("freshman", R.string.change_filter_grade_1),
    SOPHOMORE("sophomore", R.string.change_filter_grade_2),
    JUNIOR("junior", R.string.change_filter_grade_3),
    SENIOR("senior", R.string.change_filter_grade_4),
}