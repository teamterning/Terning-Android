package com.terning.data.datasourceimpl

import com.terning.data.datasource.SearchViewsDataSource
import com.terning.data.dto.response.SearchViewsResponseDto
import com.terning.data.service.SearchService
import javax.inject.Inject

class SearchViewsDataSourceImpl @Inject constructor(
    private val searchService: SearchService
): SearchViewsDataSource {
    override suspend fun getSearchViews(): SearchViewsResponseDto {
        return SearchViewsResponseDto(
            status = 200,
            message = "탐색 > 조회수 많은 공고를 조회하는데 성공했습니다",
            result = SearchViewsResponseDto.Result(
                accountments = listOf(
                    SearchViewsResponseDto.SearchViewsData(
                        internshipAnnouncementId = 23L,
                        companyImage = "https://www.google.co.kr/url?sa=i&url=https%3A%2F%2Fm.blog.naver.com%2F41minit%2F222640892626&psig=AOvVaw37hxkUffP2inv54ayG_I3K&ust=1721147168119000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCMjD_f66qYcDFQAAAAAdAAAAABAE",
                        title = "[유한킴벌리]그린캠프 w. 대학생 숲 활동가 모집"
                    ),
                    SearchViewsResponseDto.SearchViewsData(
                        internshipAnnouncementId = 3L,
                        companyImage = "https://www.google.co.kr/url?sa=i&url=https%3A%2F%2Fm.blog.naver.com%2F41minit%2F222640892626&psig=AOvVaw37hxkUffP2inv54ayG_I3K&ust=1721147168119000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCMjD_f66qYcDFQAAAAAdAAAAABAE",
                        title = "[Someone] 콘텐츠 마케터 대학생 인턴 채용"
                    )
                )
            )
        )
    }
}