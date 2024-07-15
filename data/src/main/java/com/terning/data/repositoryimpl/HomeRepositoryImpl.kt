package com.terning.data.repositoryimpl

import com.terning.data.datasource.HomeDataSource
import com.terning.domain.entity.response.HomeRecommendInternModel
import com.terning.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource,
) : HomeRepository {
    override suspend fun getRecommendIntern(sortBy: String): Result<List<HomeRecommendInternModel>> =
        runCatching {
            homeDataSource.getRecommendIntern(sortBy = sortBy).result.toRecommendInternEntity()
        }
}