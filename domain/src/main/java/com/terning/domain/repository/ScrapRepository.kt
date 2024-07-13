package com.terning.domain.repository

import com.terning.domain.entity.response.ScrapModel

interface ScrapRepository{
    suspend fun getMonthScrapList(year: Int, month: Int): Result<List<List<ScrapModel>>>
}