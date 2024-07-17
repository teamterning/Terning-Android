package com.terning.data.service

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.SearchResultResponseDto
import com.terning.data.dto.response.SearchScrapsResponseDto
import com.terning.data.dto.response.SearchViewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("/api/v1/search")
    suspend fun getSearch(
        @Query("query") keyword: String,
        @Query("sortBy") sortBy: String,
        @Query("page") page: Int,
        @Query("size") size: Int = 10,
    ): BaseResponse<SearchResultResponseDto>

    @GET("api/v1/search/views")
    suspend fun getSearchViewsList(): BaseResponse<SearchViewsResponseDto>

    @GET("api/v1/search/scraps")
    suspend fun getSearchScrapsList(): BaseResponse<SearchScrapsResponseDto>
}