package com.terning.data.service

import com.terning.data.dto.response.SearchViewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("api/v1/search/views")
    suspend fun getSearchViewsList(
        @Query("page") page: Int,
    ): SearchViewsResponseDto
}