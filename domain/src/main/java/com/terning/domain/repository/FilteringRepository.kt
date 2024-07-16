package com.terning.domain.repository

interface FilteringRepository {
    suspend fun postFiltering()
}