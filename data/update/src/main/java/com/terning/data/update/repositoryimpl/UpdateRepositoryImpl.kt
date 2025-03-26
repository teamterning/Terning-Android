package com.terning.data.update.repositoryimpl

import com.terning.data.update.datasource.UpdateDataSource
import com.terning.domain.update.repository.UpdateRepository
import javax.inject.Inject

class UpdateRepositoryImpl @Inject constructor(
    private val updateDataSource: UpdateDataSource
): UpdateRepository {
    override suspend fun fetchLatestAppVersion(callback: (version:String, title:String, content:String) -> Unit) =
        updateDataSource.fetchLatestAppVersion(callback)
}