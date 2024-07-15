package com.terning.data.repositoryimpl

import com.terning.data.datasource.ScrapDataSource
import com.terning.data.dto.request.ScrapRequestDto
import com.terning.data.dto.response.toScrapsByDeadlineMap
import com.terning.domain.entity.response.ScrapModel
import com.terning.domain.repository.ScrapRepository
import javax.inject.Inject

class ScrapRepositoryImpl @Inject constructor(
    private val scrapDataSource: ScrapDataSource
): ScrapRepository {
    override suspend fun getScrapMonth(year: Int, month: Int): Result<Map<String, List<ScrapModel>>> =
        runCatching {
            val response = scrapDataSource.getScrapMonth(
                    request = ScrapRequestDto(
                        year = year,
                        month = month
                    )
            )
            response.result.toScrapsByDeadlineMap()
        }
}