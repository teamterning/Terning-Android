package com.terning.data.datasourceimpl

import com.terning.data.datasource.SearchViewsDataSource
import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.SearchViewsResponseDto
import com.terning.data.service.SearchService
import javax.inject.Inject

class SearchViewsDataSourceImpl @Inject constructor(
    private val searchService: SearchService,
) : SearchViewsDataSource {
    override suspend fun getSearchViews(): BaseResponse<SearchViewsResponseDto> {
        return BaseResponse(
            status = 200,
            message = "",
            result = SearchViewsResponseDto(
                internshipAnnouncementId = 1,
                title = "어쩌구어쩌구어쩌구어쩌구어쩌구어쩌구어쩌구어쩌구어쩌구어쩌구어쩌구어쩌구어쩌구어쩌구어쩌구",
                companyImage = "https://image.dongascience.com/Photo/2019/09/d2468576cecf1313437de5a883bfa2ed.jpg"
            )
        )
    }
}