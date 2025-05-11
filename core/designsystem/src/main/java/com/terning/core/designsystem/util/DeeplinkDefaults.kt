package com.terning.core.designsystem.util

import android.net.Uri

object DeeplinkDefaults {
    private const val SCHEME: String = "terning"
    const val ACTION: String = "action"
    const val ID: String = "id"

    fun build(
        host: String,
        action: String? = null,
        id: String? = null
    ): String {
        val uriBuilder = Uri.Builder()
            .scheme(SCHEME)
            .authority(host)

        action?.let { uriBuilder.appendQueryParameter(ACTION, it) }
        id?.let { uriBuilder.appendQueryParameter(ID, it) }

        return uriBuilder.build().toString()
    }
}
