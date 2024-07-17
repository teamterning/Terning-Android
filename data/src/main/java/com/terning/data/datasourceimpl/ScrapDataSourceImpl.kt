package com.terning.data.datasourceimpl

import com.terning.data.datasource.ScrapDataSource
import com.terning.data.dto.NonDataBaseResponse
import com.terning.data.service.ScrapService
import com.terning.domain.entity.request.ScrapRequestModel
import javax.inject.Inject

class ScrapDataSourceImpl @Inject constructor(
    private val scrapService: ScrapService,
) : ScrapDataSource {
    override suspend fun postScrap(scrapRequestModel: ScrapRequestModel): NonDataBaseResponse =
        scrapService.postScrap(
            scrapRequestModel.internshipAnnouncementId,
            scrapRequestModel.color
        )
}