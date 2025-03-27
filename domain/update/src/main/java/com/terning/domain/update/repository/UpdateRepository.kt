package com.terning.domain.update.repository

import com.terning.domain.update.entity.UpdateMessage

interface UpdateRepository {
    suspend fun fetchLatestAppVersion(callback: (message: UpdateMessage) -> Unit)
}