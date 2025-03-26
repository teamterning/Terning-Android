package com.terning.domain.update.repository

interface UpdateRepository {
    suspend fun fetchLatestAppVersion(callback: (version:String, title:String, content:String) -> Unit)
}