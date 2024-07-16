package com.terning.domain.repository

interface ScrapRepository {
    suspend fun postScrap(id: Long, color: Int)
}