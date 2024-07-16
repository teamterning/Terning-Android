package com.terning.data.datasourceimpl

import com.terning.data.datasource.ScrapDataSource
import com.terning.data.dto.NonDataBaseResponse
import com.terning.data.service.ScrapService
import javax.inject.Inject

class ScrapDataSourceImpl @Inject constructor(
    private val scrapService: ScrapService,
) : ScrapDataSource {
    override suspend fun postScrap(id: Long, color: Int): NonDataBaseResponse =
        scrapService.postScrap(id, color)
}