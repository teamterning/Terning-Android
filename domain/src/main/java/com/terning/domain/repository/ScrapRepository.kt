package com.terning.domain.repository

import com.terning.domain.entity.request.ScrapRequestModel

interface ScrapRepository {
    suspend fun postScrap(scrapRequestModel: ScrapRequestModel): Result<Unit>
    suspend fun deleteScrap(scrapRequestModel: ScrapRequestModel): Result<Unit>
    suspend fun patchScrap(scrapRequestModel: ScrapRequestModel): Result<Unit>
}