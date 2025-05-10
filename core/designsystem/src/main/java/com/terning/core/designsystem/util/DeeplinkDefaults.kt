package com.terning.core.designsystem.util

import android.net.Uri

object DeeplinkDefaults {
    private const val SCHEME: String = "terning"
    const val REDIRECT: String = "redirect"
    const val INTERN_ID: String = "internId"

    fun build(
        host: String,
        redirect: String? = null,
        internId: String? = null
    ): String {
        val uriBuilder = Uri.Builder()
            .scheme(SCHEME)
            .authority(host)

        redirect?.let { uriBuilder.appendQueryParameter(REDIRECT, it) }
        internId?.let { uriBuilder.appendQueryParameter(INTERN_ID, it) }

        return uriBuilder.build().toString()
    }
}
