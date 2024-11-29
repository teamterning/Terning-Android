package com.terning.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class TerningDataStoreImpl @Inject constructor(
    private val dataStore: SharedPreferences,
) : TerningDataStore {
    override var accessToken: String
        get() = dataStore.getString(ACCESS_TOKEN, "") ?: ""
        set(value) = dataStore.edit { putString(ACCESS_TOKEN, value) }

    override var refreshToken: String
        get() = dataStore.getString(REFRESH_TOKEN, "") ?: ""
        set(value) = dataStore.edit { putString(REFRESH_TOKEN, value) }

    override var userId: Long
        get() = dataStore.getLong(USER_ID, 0L)
        set(value) = dataStore.edit { putLong(USER_ID, value) }

    override fun clearInfo() {
        dataStore.edit().clear().commit()
    }

    companion object {
        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val REFRESH_TOKEN = "REFRESH_TOKEN"
        private const val USER_ID = "USER_ID"
    }
}