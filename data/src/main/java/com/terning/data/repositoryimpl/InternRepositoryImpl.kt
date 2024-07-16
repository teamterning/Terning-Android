package com.terning.data.repositoryimpl

import com.terning.data.datasource.InternDataSource
import com.terning.domain.repository.InternRepository
import javax.inject.Inject

class InternRepositoryImpl @Inject constructor(
    private val internDataSource: InternDataSource,
) : InternRepository {
    override suspend fun getInternInfo() {
        runCatching {
            internDataSource.getInternInfo(1).result
        }
    }
}