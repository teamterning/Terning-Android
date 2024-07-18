package com.terning.data.datasourceimpl

import com.terning.data.datasource.ScrapDataSource
import com.terning.data.dto.NonDataBaseResponse
import com.terning.data.dto.request.ScrapColorRequestDto
import com.terning.data.service.ScrapService
import com.terning.domain.entity.request.ScrapRequestModel
import javax.inject.Inject

class ScrapDataSourceImpl @Inject constructor(
    private val scrapService: ScrapService,
) : ScrapDataSource {
    override suspend fun postScrap(scrapRequestModel: ScrapRequestModel): NonDataBaseResponse =
        scrapService.postScrap(
            scrapRequestModel.id,
            ScrapColorRequestDto(scrapRequestModel.color)
        )

    override suspend fun deleteScrap(scrapRequestModel: ScrapRequestModel): NonDataBaseResponse =
        scrapService.deleteScrap(scrapRequestModel.id)

    override suspend fun patchScrap(scrapRequestModel: ScrapRequestModel): NonDataBaseResponse =
        scrapService.patchScrap(
            scrapRequestModel.id,
            ScrapColorRequestDto(scrapRequestModel.color)
        )
}