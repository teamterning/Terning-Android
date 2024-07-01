package com.terning.data.datasource

import com.terning.data.dto.response.MockResponseDto

interface MockDataSource {
    suspend fun getMock(page: Int): MockResponseDto
}