package com.terning.domain.repository

import com.terning.domain.entity.response.SearchViewsResponseModel

interface SearchViewsRepository {
    suspend fun getSearchViewsList(): Result<List<SearchViewsResponseModel>>
}