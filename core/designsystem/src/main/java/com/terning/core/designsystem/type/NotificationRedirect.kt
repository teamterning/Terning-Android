package com.terning.core.designsystem.type

enum class DeeplinkHost(val path: String) {
    CALENDAR("calendar"),
    HOME("home"),
    SEARCH("search"),
    KAKAOLINK("kakaolink"),
    INTERN("intern");

    companion object {
        fun from(type: String?): DeeplinkHost? =
            entries.firstOrNull { it.path.equals(type, ignoreCase = true) }
    }
}
