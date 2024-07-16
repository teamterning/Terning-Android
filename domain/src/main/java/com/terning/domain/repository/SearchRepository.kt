package com.terning.domain.repository

import com.terning.domain.entity.response.SearchViewsResponseModel

interface SearchRepository {
    suspend fun getSearchViewsList(): Result<List<SearchViewsResponseModel>>
}