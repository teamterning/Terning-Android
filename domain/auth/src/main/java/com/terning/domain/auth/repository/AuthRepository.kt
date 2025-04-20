package com.terning.domain.auth.repository

import com.terning.domain.auth.entity.SignInRequest
import com.terning.domain.auth.entity.SignInResponse
import com.terning.domain.auth.entity.SignUpRequest
import com.terning.domain.auth.entity.SignUpResponse

interface AuthRepository {

    suspend fun signIn(
        authorization: String,
        request: SignInRequest
    ): Result<SignInResponse>

    suspend fun signUp(
        authId: String,
        request: SignUpRequest
    ): Result<SignUpResponse>

}