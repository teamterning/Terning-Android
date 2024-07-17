package com.terning.data.repositoryimpl

import com.terning.data.datasource.HomeDataSource
import com.terning.domain.entity.response.HomeTodayInternModel
import com.terning.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource,
) : HomeRepository {
    override suspend fun getHomeTodayInternList(): Result<List<HomeTodayInternModel>> =
        runCatching {
            homeDataSource.getTodayIntern().result.map {
                it.toHomeTodayInternList()
            }
        }
}