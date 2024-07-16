package com.terning.domain.repository

interface InternRepository {
    suspend fun getInternInfo()
}