package com.terning.data.datasourceimpl

import com.terning.data.datasource.SearchScrapsDataSource
import com.terning.data.dto.response.SearchScrapsResponseDto
import com.terning.data.service.SearchService
import javax.inject.Inject

class SearchScrapsDataSourceImpl @Inject constructor(
    private val searchService: SearchService,
) : SearchScrapsDataSource {
    override suspend fun getSearchScraps(): SearchScrapsResponseDto {
        return SearchScrapsResponseDto(
            status = 200,
            message = "탐색 > 스크랩 많은 공고를 조회하는데 성공했습니다",
            result = SearchScrapsResponseDto.Result(
                accountments = listOf(
                    SearchScrapsResponseDto.SearchScrapsData(
                        internshipAnnouncementId = 23L,
                        companyImage = "https://image.dongascience.com/Photo/2019/09/d2468576cecf1313437de5a883bfa2ed.jpg",
                        title = "[유한킴벌리]그린캠프 w. 대학생 숲 활동가 모집"
                    ),
                    SearchScrapsResponseDto.SearchScrapsData(
                        internshipAnnouncementId = 3L,
                        companyImage = "https://https://image.dongascience.com/Photo/2019/09/d2468576cecf1313437de5a883bfa2ed.jpg",
                        title = "[Someone] 콘텐츠 마케터 대학생 인턴 채용"
                    ),
                    SearchScrapsResponseDto.SearchScrapsData(
                        internshipAnnouncementId = 23L,
                        companyImage = "https://image.dongascience.com/Photo/2019/09/d2468576cecf1313437de5a883bfa2ed.jpg",
                        title = "[유한킴벌리]그린캠프 w. 대학생 숲 활동가 모집"
                    ),
                    SearchScrapsResponseDto.SearchScrapsData(
                        internshipAnnouncementId = 3L,
                        companyImage = "https://https://image.dongascience.com/Photo/2019/09/d2468576cecf1313437de5a883bfa2ed.jpg",
                        title = "[Someone] 콘텐츠 마케터 대학생 인턴 채용"
                    )
                )
            )
        )
    }
}