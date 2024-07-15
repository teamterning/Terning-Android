package com.terning.data.datasourceimpl

import com.terning.data.datasource.SearchScarpsDataSource
import com.terning.data.dto.response.SearchScarpsResponseDto
import com.terning.data.service.SearchService
import javax.inject.Inject

class SearchScarpsDataSourceImpl @Inject constructor(
    private val searchService: SearchService,
) : SearchScarpsDataSource {
    override suspend fun getSearchScarps(): SearchScarpsResponseDto {
        return SearchScarpsResponseDto(
            status = 200,
            message = "탐색 > 스크랩 많은 공고를 조회하는데 성공했습니다",
            result = SearchScarpsResponseDto.Result(
                accountments = listOf(
                    SearchScarpsResponseDto.SearchScrapsData(
                        internshipAnnouncementId = 23L,
                        companyImage = "https://image.dongascience.com/Photo/2019/09/d2468576cecf1313437de5a883bfa2ed.jpg",
                        title = "[유한킴벌리]그린캠프 w. 대학생 숲 활동가 모집"
                    ),
                    SearchScarpsResponseDto.SearchScrapsData(
                        internshipAnnouncementId = 3L,
                        companyImage = "https://https://image.dongascience.com/Photo/2019/09/d2468576cecf1313437de5a883bfa2ed.jpg",
                        title = "[Someone] 콘텐츠 마케터 대학생 인턴 채용"
                    )
                )
            )
        )
    }
}