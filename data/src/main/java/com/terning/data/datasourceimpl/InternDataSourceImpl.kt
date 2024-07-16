package com.terning.data.datasourceimpl

import com.terning.data.datasource.InternDataSource
import com.terning.data.dto.response.InternResponseDto
import com.terning.data.service.InternService
import javax.inject.Inject

class InternDataSourceImpl @Inject constructor(
    private val internService: InternService,
) : InternDataSource {
    override suspend fun getInternInfo(internshipAnnouncementId: Int): InternResponseDto =
        internService.getInternInfo(1)
}