package com.terning.core.firebase.remoteconfig

import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.terning.core.firebase.remoteconfig.RemoteConfigKey.LATEST_APP_VERSION
import com.terning.core.firebase.remoteconfig.RemoteConfigKey.MAJOR_UPDATE_BODY
import com.terning.core.firebase.remoteconfig.RemoteConfigKey.MAJOR_UPDATE_TITLE
import timber.log.Timber

object RemoteConfigUtil {
    private const val TAG = "FirebaseRemoteConfig"

    private val remoteConfig: FirebaseRemoteConfig by lazy {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        Firebase.remoteConfig.apply {
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(
                mapOf(
                    LATEST_APP_VERSION.key to "",
                    MAJOR_UPDATE_BODY.key to "",
                    MAJOR_UPDATE_TITLE.key to "",
                )
            )
        }
    }

    fun onVersionFetchCompleteListener(callback: (Map<RemoteConfigKey, String>) -> Unit) {
        try {
            val fetchTask = remoteConfig.fetchAndActivate()
            fetchTask.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val map = mutableMapOf(
                        getConfigKeyToStringPair(LATEST_APP_VERSION),
                        getConfigKeyToStringPair(MAJOR_UPDATE_TITLE),
                        getConfigKeyToStringPair(MAJOR_UPDATE_BODY),
                    )
                    callback(map)
                }
            }
        } catch (e: Exception) {
            Timber.tag(TAG).e(e.printStackTrace().toString())
        }
    }


    private fun getConfigKeyToStringPair(configKey: RemoteConfigKey): Pair<RemoteConfigKey, String> =
        configKey to remoteConfig.getString(configKey.key)
}


