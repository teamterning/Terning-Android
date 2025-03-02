package com.terning.data.search.service

import com.terning.core.network.BaseResponse
import com.terning.data.search.dto.response.SearchAnnouncementResponseDto
import com.terning.data.search.dto.response.SearchBannersResponseDto
import com.terning.data.search.dto.response.SearchResultResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("/api/v1/search")
    suspend fun getSearch(
        @Query("keyword") keyword: String,
        @Query("sortBy") sortBy: String,
        @Query("page") page: Int,
        @Query("size") size: Int = 10,
    ): BaseResponse<SearchResultResponseDto>

    @GET("api/v1/search/views")
    suspend fun getSearchViewsList(): BaseResponse<SearchAnnouncementResponseDto>

    @GET("api/v1/search/scraps")
    suspend fun getSearchScrapsList(): BaseResponse<SearchAnnouncementResponseDto>

    @GET("api/v1/search/banners")
    suspend fun getSearchBannerList(): BaseResponse<SearchBannersResponseDto>
}