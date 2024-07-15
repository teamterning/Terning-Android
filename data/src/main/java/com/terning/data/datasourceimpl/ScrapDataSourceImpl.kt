package com.terning.data.datasourceimpl

import com.terning.data.datasource.ScrapDataSource
import com.terning.data.dto.BaseResponse
import com.terning.data.dto.request.ScrapRequestDto
import com.terning.data.dto.response.ScrapResponseDto
import com.terning.data.dto.response.ScrapResponsesDto
import com.terning.data.dto.response.getMockScrapList
import javax.inject.Inject

class ScrapDataSourceImpl @Inject constructor(

) : ScrapDataSource {
    override suspend fun getScrapMonth(request: ScrapRequestDto): BaseResponse<List<ScrapResponsesDto>> =
        BaseResponse(
            status = 200,
            message = "(월간) 스크랩된 공고 정보가 성공적으로 로드되었습니다.",
            result = getMockScrapList(request.year, request.month)
        )

}