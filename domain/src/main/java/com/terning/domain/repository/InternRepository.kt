package com.terning.domain.repository

import com.terning.domain.entity.intern.InternInfoModel

interface InternRepository {
    suspend fun getInternInfo(id: Long): Result<InternInfoModel>
}