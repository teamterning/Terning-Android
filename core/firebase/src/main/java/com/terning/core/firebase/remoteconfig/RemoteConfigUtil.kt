package com.terning.core.firebase.remoteconfig

import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

object RemoteConfigModule {
    const val LATEST_APP_VERSION = "android_app_version"
    const val MAJOR_UPDATE_TITLE = "android_update_title"
    const val MAJOR_UPDATE_BODY = "android_update_body"
    private const val TAG = "FirebaseRemoteConfig"

    private val remoteConfig: FirebaseRemoteConfig by lazy {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        Firebase.remoteConfig.apply {
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(
                mapOf(
                    LATEST_APP_VERSION to "",
                    MAJOR_UPDATE_BODY to "",
                    MAJOR_UPDATE_TITLE to "",
                )
            )
        }
    }

    suspend fun onFetchCompleteListener(callback: (Map<String, String>) -> Unit) {
        withContext(Dispatchers.IO) {
            try {
                val fetchTask = remoteConfig.fetchAndActivate()
                fetchTask.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val map = mutableMapOf(
                            LATEST_APP_VERSION to remoteConfig.getString(LATEST_APP_VERSION),
                            MAJOR_UPDATE_TITLE to remoteConfig.getString(MAJOR_UPDATE_BODY),
                            MAJOR_UPDATE_BODY to remoteConfig.getString(MAJOR_UPDATE_TITLE),
                        )
                        callback(map)
                    }
                }
            } catch (e: Exception) {
                Timber.tag(TAG).e(e.printStackTrace().toString())
            }
        }
    }
}
