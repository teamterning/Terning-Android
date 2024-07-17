package com.terning.domain.repository

import com.terning.domain.entity.response.InternInfoModel

interface InternRepository {
    suspend fun getInternInfo(id: Long): Result<InternInfoModel>
}