package com.terning.data.datasource

import com.terning.data.dto.NonDataBaseResponse
import com.terning.domain.entity.request.ScrapRequestModel

interface ScrapDataSource {
    suspend fun postScrap(
        scrapRequestModel: ScrapRequestModel,
    ): NonDataBaseResponse

    suspend fun deleteScrap(
        scrapRequestModel: ScrapRequestModel,
    ): NonDataBaseResponse

    suspend fun patchScrap(
        scrapRequestModel: ScrapRequestModel,
    ): NonDataBaseResponse
}