package com.terning.domain.intern.repository

import com.terning.domain.entity.intern.InternInfo

interface InternRepository {
    suspend fun getInternInfo(id: Long): Result<InternInfo>
}