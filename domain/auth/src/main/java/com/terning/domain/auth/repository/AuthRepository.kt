package com.terning.domain.auth.repository

import com.terning.domain.entity.onboarding.SignInRequest
import com.terning.domain.entity.onboarding.SignUpRequest
import com.terning.domain.entity.onboarding.SignInResponse
import com.terning.domain.entity.onboarding.SignUpResponse

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