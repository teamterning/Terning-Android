package com.terning.data.update.repositoryimpl

import com.terning.core.firebase.remoteconfig.RemoteConfigKey
import com.terning.data.update.datasource.UpdateDataSource
import com.terning.domain.update.entity.UpdateMessage
import com.terning.domain.update.repository.UpdateRepository
import timber.log.Timber
import javax.inject.Inject

class UpdateRepositoryImpl @Inject constructor(
    private val updateDataSource: UpdateDataSource
) : UpdateRepository {
    override suspend fun fetchLatestAppVersion(callback: (UpdateMessage) -> Unit) =
        updateDataSource.fetchLatestAppVersion {
            Timber.tag("Update").d(it.toString())
            callback(
                UpdateMessage(
                    version = it[RemoteConfigKey.LATEST_APP_VERSION] ?: "0.0.0",
                    majorUpdateTitle = it[RemoteConfigKey.MAJOR_UPDATE_TITLE] ?: "",
                    majorUpdateBody = it[RemoteConfigKey.MAJOR_UPDATE_BODY] ?: "",
                    patchUpdateTitle = it[RemoteConfigKey.PATCH_UPDATE_TITLE] ?: "",
                    patchUpdateBody = it[RemoteConfigKey.PATCH_UPDATE_BODY] ?: "",
                )
            )
        }
}