package com.terning.domain.repository

import com.terning.domain.entity.response.MockResponseModel

interface MockRepository {
    suspend fun getMockList(page: Int): Result<List<MockResponseModel>>
}