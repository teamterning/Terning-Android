package com.terning.domain.repository

import com.terning.domain.entity.auth.SignInRequest
import com.terning.domain.entity.auth.SignUpRequest
import com.terning.domain.entity.auth.SignInResponse
import com.terning.domain.entity.auth.SignUpResponse

interface AuthRepository {

    suspend fun postSignIn(
        authorization: String,
        request: SignInRequest
    ): Result<SignInResponse>

    suspend fun postSignUp(
        authId: String,
        request: SignUpRequest
    ): Result<SignUpResponse>

}