package com.terning.domain.repository

import com.terning.domain.entity.response.InternViewsResponseModel

interface SearchViewsRepository {
    suspend fun getSearchViewsList(): Result<List<InternViewsResponseModel>>
}