package com.terning.data.datasourceimpl

import com.terning.data.datasource.SearchViewsDataSource
import com.terning.data.dto.response.InternAnnouncementResponseDto
import com.terning.data.service.SearchService
import javax.inject.Inject

class SearchViewsDataSourceImpl @Inject constructor(
    private val searchService: SearchService
): SearchViewsDataSource {
    override suspend fun getSearchViews(): InternAnnouncementResponseDto {
        return InternAnnouncementResponseDto(
            status = 200,
            message = "탐색 > 조회수 많은 공고를 조회하는데 성공했습니다",
            result = InternAnnouncementResponseDto.Result(
                accountments = listOf(
                    InternAnnouncementResponseDto.InternAnnouncementData(
                        internshipAnnouncementId = 23L,
                        companyImage = "https://image.dongascience.com/Photo/2019/09/d2468576cecf1313437de5a883bfa2ed.jpg",
                        title = "[유한킴벌리]그린캠프 w. 대학생 숲 활동가 모집"
                    ),
                    InternAnnouncementResponseDto.InternAnnouncementData(
                        internshipAnnouncementId = 3L,
                        companyImage = "https://https://image.dongascience.com/Photo/2019/09/d2468576cecf1313437de5a883bfa2ed.jpg",
                        title = "[Someone] 콘텐츠 마케터 대학생 인턴 채용"
                    )
                )
            )
        )
    }
}