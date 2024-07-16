package com.terning.data.service

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.SearchViewsResponseDto
import retrofit2.http.GET

interface SearchService {
    @GET("api/v1/search/views")
    suspend fun getSearchViewsList(): BaseResponse<SearchViewsResponseDto>
}