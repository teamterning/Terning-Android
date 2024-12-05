package com.terning.data.auth.repositoryimpl

import com.terning.data.auth.datasource.AuthDataSource
import com.terning.data.auth.mapper.toSignInRequestDto
import com.terning.data.auth.mapper.toSignInResponse
import com.terning.data.auth.mapper.toSignUpRequestDto
import com.terning.data.auth.mapper.toSignUpResponse
import com.terning.domain.auth.entity.SignInRequest
import com.terning.domain.auth.entity.SignInResponse
import com.terning.domain.auth.entity.SignUpRequest
import com.terning.domain.auth.entity.SignUpResponse
import com.terning.domain.auth.repository.AuthRepository
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