package com.terning.domain.intern.repository

import com.terning.domain.intern.entity.InternInfo

interface InternRepository {
    suspend fun getInternInfo(id: Long): Result<InternInfo>
}