package com.terning.data.intern.repositoryimpl

import com.terning.data.intern.mapper.toInternInfo
import com.terning.domain.entity.intern.InternInfo
import com.terning.domain.repository.InternRepository
import javax.inject.Inject

class InternRepositoryImpl @Inject constructor(
    private val internDataSource: com.terning.data.intern.datasource.InternDataSource,
) : InternRepository {
    override suspend fun getInternInfo(id: Long): Result<InternInfo> = runCatching {
        internDataSource.getInternInfo(id).result.toInternInfo()
    }
}