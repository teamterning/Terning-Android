package com.terning.core.type

import androidx.annotation.StringRes
import com.terning.core.R

enum class Grade(
    val stringValue: String,
    @StringRes val stringResId: Int,
) {
    FRESHMAN("freshman", R.string.change_filter_grade_1),
    SOPHOMORE("sophomore", R.string.change_filter_grade_2),
    JUNIOR("junior", R.string.change_filter_grade_3),
    SENIOR("senior", R.string.change_filter_grade_4);

    companion object {
        fun fromString(value: String?): Grade = when (value) {
            "freshman" -> FRESHMAN
            "sophomore" -> SOPHOMORE
            "junior" -> JUNIOR
            "senior" -> SENIOR
            else -> FRESHMAN
        }

        fun toIndex(grade: Grade): Int =
            Grade.entries.indexOf(grade)
    }
}