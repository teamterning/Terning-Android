package com.terning.data.datasourceimpl

import com.terning.data.datasource.SearchScrapsDataSource
import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.SearchScrapsResponseDto
import com.terning.data.service.SearchService
import javax.inject.Inject

class SearchScrapsDataSourceImpl @Inject constructor(
    private val searchService: SearchService,
) : SearchScrapsDataSource {
    override suspend fun getSearchScraps(): BaseResponse<SearchScrapsResponseDto> {

        return BaseResponse(
            status = 200,
            message = "Success",
            result = SearchScrapsResponseDto(
                internshipAnnouncementId = 5,
                title = "어쩌구",
                companyImage = "https://image.dongascience.com/Photo/2019/09/d2468576cecf1313437de5a883bfa2ed.jpg"
            )
        )
    }
}