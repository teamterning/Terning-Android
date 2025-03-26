package com.terning.data.update.datasource

import com.terning.core.firebase.remoteconfig.RemoteConfigKey
import com.terning.core.firebase.remoteconfig.TerningRemoteConfig
import javax.inject.Inject

class UpdateDataSource @Inject constructor(
    private val remoteConfig: TerningRemoteConfig,
) {
    fun fetchLatestAppVersion(callback: (version:String, title:String, body:String) -> Unit) =
        remoteConfig.addOnVersionFetchCompleteListener {
            callback(
                it[RemoteConfigKey.LATEST_APP_VERSION] ?: "0.0.0",
                it[RemoteConfigKey.MAJOR_UPDATE_TITLE] ?: "",
                it[RemoteConfigKey.MAJOR_UPDATE_BODY] ?: ""
            )
        }


}