package com.terning.data.update.datasource

import com.terning.core.firebase.remoteconfig.RemoteConfigKey
import com.terning.core.firebase.remoteconfig.TerningRemoteConfig
import javax.inject.Inject

class UpdateDataSource @Inject constructor(
    private val remoteConfig: TerningRemoteConfig,
) {
    fun fetchLatestAppVersion(callback: (Map<RemoteConfigKey, String>) -> Unit) =
        remoteConfig.addOnVersionFetchCompleteListener {
            callback(it)
        }
}