package com.terning.data.datasource

import com.terning.data.dto.NonDataBaseResponse

interface ScrapDataSource {
    suspend fun postScrap(id: Int, color: Int): NonDataBaseResponse
}