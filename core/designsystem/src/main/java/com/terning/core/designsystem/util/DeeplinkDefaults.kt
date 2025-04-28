package com.terning.core.designsystem.util

object DeeplinkDefaults {
    const val REDIRECT: String = "redirect"
    fun build(base: String) = "terning://${base}"
}