package com.terning.core.analytics

enum class EventType(val prefix: String) {
    SIGNUP("signup"),
    SCREEN("screen"),
    CLICK("click"),
    SCROLL("scroll"),
    PUSH_NOTIFICATION("push_notification")
}
