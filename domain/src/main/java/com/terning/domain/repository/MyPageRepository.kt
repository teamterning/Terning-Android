package com.terning.domain.repository

interface MyPageRepository {
    suspend fun postLogout() : Result<Unit>
}