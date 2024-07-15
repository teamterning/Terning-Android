package com.terning.data.service

import com.terning.data.dto.response.SearchScrapsResponseDto
import com.terning.data.dto.response.SearchViewsResponseDto
import retrofit2.http.GET

interface SearchService {
    @GET("api/v1/search/views")
    suspend fun getSearchViewsList(): SearchViewsResponseDto {
        return SearchViewsResponseDto(
            status = 200,
            message = "탐색 > 조회수 많은 공고를 조회하는데 성공했습니다",
            result = SearchViewsResponseDto.Result(
                accountments = listOf(
                    SearchViewsResponseDto.SearchViewsData(
                        internshipAnnouncementId = 23,
                        companyImage = "https://example.com/image1.jpg",
                        title = "[유한킴벌리]그린캠프 w. 대학생 숲 활동가 모집"
                    ),
                    SearchViewsResponseDto.SearchViewsData(
                        internshipAnnouncementId = 3,
                        companyImage = "https://example.com/image2.jpg",
                        title = "[Someone] 콘텐츠 마케터 대학생 인턴 채용"
                    )
                )
            )
        )
    }

    @GET("api/v1/search/scraps")
    suspend fun getSearchScrapsList(): SearchScrapsResponseDto {
        return SearchScrapsResponseDto(
            status = 200,
            message = "탐색 > 스크랩 많은 공고를 조회하는데 성공했습니다",
            result = SearchScrapsResponseDto.Result(
                accountments = listOf(
                    SearchScrapsResponseDto.SearchScrapsData(
                        internshipAnnouncementId = 23,
                        companyImage = "https://example.com/image1.jpg",
                        title = "[유한킴벌리] 그린캠프 w. 대학생 숲 활동가 모집"
                    ),
                    SearchScrapsResponseDto.SearchScrapsData(
                        internshipAnnouncementId = 3,
                        companyImage = "https://example.com/image2.jpg",
                        title = "[Someone] 콘텐츠 마케터 대학생 인턴 채용"
                    ),
                    SearchScrapsResponseDto.SearchScrapsData(
                        internshipAnnouncementId = 23,
                        companyImage = "https://example.com/image1.jpg",
                        title = "[유한킴벌리] 그린캠프 w. 대학생 숲 활동가 모집"
                    ),
                    SearchScrapsResponseDto.SearchScrapsData(
                        internshipAnnouncementId = 3,
                        companyImage = "https://example.com/image2.jpg",
                        title = "[Someone] 콘텐츠 마케터 대학생 인턴 채용"
                    ),
                )
            )
        )
    }
}