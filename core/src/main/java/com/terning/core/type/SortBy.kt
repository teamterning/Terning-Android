package com.terning.core.type

import androidx.annotation.StringRes
import com.terning.core.R

enum class SortBy(@StringRes val type: Int) {
    EARLIEST(R.string.sort_by_earliest),
    SHORTEST(R.string.sort_by_shortest),
    LONGEST(R.string.sort_by_longest),
    SCRAP(R.string.sort_by_scrap),
    VIEW_COUNT(R.string.sort_by_view_count),
}