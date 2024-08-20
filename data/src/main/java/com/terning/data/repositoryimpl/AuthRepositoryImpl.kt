package com.terning.data.repositoryimpl

import com.terning.data.datasource.AuthDataSource
import com.terning.data.mapper.onboarding.toSignInRequestDto
import com.terning.data.mapper.onboarding.toSignInResponse
import com.terning.data.mapper.onboarding.toSignUpRequestDto
import com.terning.data.mapper.onboarding.toSignUpResponse
import com.terning.domain.entity.onboarding.SignInRequest
import com.terning.domain.entity.onboarding.SignInResponse
import com.terning.domain.entity.onboarding.SignUpRequest
import com.terning.domain.entity.onboarding.SignUpResponse
import com.terning.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {

    override suspend fun postSignIn(
        authorization: String,
        request: SignInRequest
    ): Result<SignInResponse> = runCatching {
        authDataSource.postSignIn(
            authorization,
            request.toSignInRequestDto()
        ).result.toSignInResponse()
    }

    override suspend fun postSignUp(
        authId: String,
        request: SignUpRequest
    ): Result<SignUpResponse> = runCatching {
        authDataSource.postSignUp(
            authId,
            request.toSignUpRequestDto()
        ).result.toSignUpResponse()
    }
}