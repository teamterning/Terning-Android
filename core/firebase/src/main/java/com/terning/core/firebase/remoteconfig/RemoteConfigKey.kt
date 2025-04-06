package com.terning.core.firebase.remoteconfig

enum class RemoteConfigKey(
    val key: String
) {
    LATEST_APP_VERSION(key = "android_app_version"),
    MAJOR_UPDATE_TITLE(key = "android_update_title"),
    MAJOR_UPDATE_BODY(key = "android_update_body");
}