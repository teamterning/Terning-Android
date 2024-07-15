package com.terning.domain.repository

import com.terning.domain.entity.response.ScrapModel

interface ScrapRepository{
    suspend fun getScrapMonth(year: Int, month: Int): Result<Map<String, List<ScrapModel>>>
}