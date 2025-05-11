package com.terning.core.designsystem.type

enum class DeeplinkType(val path: String) {
    CALENDAR("calendar"),
    HOME("home"),
    SEARCH("search"),
    KAKAOLINK("kakaolink"),
    INTERN("intern");

    companion object {
        fun from(type: String?): DeeplinkType? =
            entries.firstOrNull { it.path.equals(type, ignoreCase = true) }
    }
}
