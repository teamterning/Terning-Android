package com.terning.domain.repository

interface MyPageRepository {
    suspend fun patchLogout() : Result<Unit>
}