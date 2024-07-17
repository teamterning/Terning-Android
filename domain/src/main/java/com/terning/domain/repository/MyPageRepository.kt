package com.terning.domain.repository

interface MyPageRepository {
    suspend fun postLogout() : Result<Unit>

    suspend fun deleteQuit() : Result<Unit>
}