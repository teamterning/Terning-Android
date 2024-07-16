package com.terning.data.repositoryimpl

import com.terning.data.datasource.ScrapDataSource
import com.terning.domain.repository.ScrapRepository
import javax.inject.Inject

class ScrapRepositoryImpl @Inject constructor(
    private val scrapDataSource: ScrapDataSource,
) : ScrapRepository {
    override suspend fun postScrap(id: Int, color: Int) {
        scrapDataSource.postScrap(id, color)
    }
}