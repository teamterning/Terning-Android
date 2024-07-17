package com.terning.domain.repository

import com.terning.domain.entity.response.HomeTodayInternModel

interface HomeRepository {
    suspend fun getHomeTodayInternList(): Result<List<HomeTodayInternModel>>
}