package com.terning.core.designsystem.type

enum class NotificationRedirect(val path: String) {
    CALENDAR("calendar"),
    HOME("home"),
    SEARCH("search");

    companion object {
        fun from(type: String?): NotificationRedirect? =
            entries.firstOrNull { it.path.equals(type, ignoreCase = true) }
    }
}
