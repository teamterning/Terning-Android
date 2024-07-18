package com.terning.feature.home.home.model

enum class SortBy(val sortBy: String) {
    EARLIEST("deadlineSoon"),
    SHORTEST("shortestDuration"),
    LONGEST("longestDuration"),
    SCRAP("mostScrapped"),
    VIEW_COUNT("mostViewed"),
}