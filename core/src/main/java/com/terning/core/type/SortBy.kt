package com.terning.core.type

import androidx.annotation.StringRes
import com.terning.core.R

enum class SortBy(
    @StringRes val sortBy: Int,
    val type: String,
) {
    EARLIEST(R.string.sort_by_earliest, "deadlineSoon"),
    SHORTEST(R.string.sort_by_shortest, "shortestDuration"),
    LONGEST(R.string.sort_by_longest, "longestDuration"),
    SCRAP(R.string.sort_by_scrap, "mostScrapped"),
    VIEW_COUNT(R.string.sort_by_view_count, "mostViewed"),
}
