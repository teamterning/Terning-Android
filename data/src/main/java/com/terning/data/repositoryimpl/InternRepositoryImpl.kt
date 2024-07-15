package com.terning.data.repositoryimpl

import com.terning.data.datasource.InternDataSource
import com.terning.domain.entity.response.RecommendInternModel
import com.terning.domain.repository.InternRepository
import javax.inject.Inject

class InternRepositoryImpl @Inject constructor(
    private val internDataSource: InternDataSource,
) : InternRepository {
    override suspend fun getRecommendIntern(sortBy: String): Result<List<RecommendInternModel>> =
        runCatching {
            internDataSource.getRecommendIntern(sortBy = sortBy).result.toRecommendInternEntity()
        }
}