package com.terning.domain.repository

import com.terning.domain.entity.response.ScrapModel

interface ScrapRepository{
    suspend fun getMonthScrapList(year: Int, month: Int): Result<List<List<ScrapModel>>>

    suspend fun getMonthScrapLists(year: Int, month: Int): Result<Map<String, List<ScrapModel>>>
}