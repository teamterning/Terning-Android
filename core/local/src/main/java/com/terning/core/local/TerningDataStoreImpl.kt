package com.terning.core.local

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class TerningDataStoreImpl @Inject constructor(
    private val dataStore: SharedPreferences,
) : TerningDataStore {
    override var accessToken: String
        get() = dataStore.getString(ACCESS_TOKEN, "").orEmpty()
        set(value) = dataStore.edit { putString(ACCESS_TOKEN, value) }

    override var refreshToken: String
        get() = dataStore.getString(REFRESH_TOKEN, "").orEmpty()
        set(value) = dataStore.edit { putString(REFRESH_TOKEN, value) }

    override var fcmToken: String
        get() = dataStore.getString(FCM_TOKEN, "").orEmpty()
        set(value) = dataStore.edit { putString(FCM_TOKEN, value) }

    override var userId: Long
        get() = dataStore.getLong(USER_ID, 0L)
        set(value) = dataStore.edit { putLong(USER_ID, value) }

    override var alarmAvailable: Boolean
        get() = dataStore.getBoolean(ALARM, false)
        set(value) = dataStore.edit { putBoolean(ALARM, value) }

    override var hasRequestedPermission: Boolean
        get() = dataStore.getBoolean(PERMISSION_REQUESTED, false)
        set(value) = dataStore.edit { putBoolean(PERMISSION_REQUESTED, value) }

    override fun clearInfo() {
        dataStore.edit().clear().apply()
    }

    companion object {
        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val REFRESH_TOKEN = "REFRESH_TOKEN"
        private const val FCM_TOKEN = "FCM_TOKEN"
        private const val USER_ID = "USER_ID"
        private const val ALARM = "ALARM"
        private const val PERMISSION_REQUESTED = "PERMISSION_REQUESTED"
    }
}