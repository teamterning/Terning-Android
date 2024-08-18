package com.terning.data.repositoryimpl

import com.terning.data.datasource.AuthDataSource
import com.terning.data.mapper.auth.toSignInModel
import com.terning.data.mapper.auth.toSignInRequestDto
import com.terning.data.mapper.auth.toSignUpModel
import com.terning.data.mapper.auth.toSignUpRequestDto
import com.terning.domain.entity.auth.SignInRequestModel
import com.terning.domain.entity.auth.SignInResponseModel
import com.terning.domain.entity.auth.SignUpRequestModel
import com.terning.domain.entity.auth.SignUpResponseModel
import com.terning.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {

    override suspend fun postSignIn(
        authorization: String,
        request: SignInRequestModel
    ): Result<SignInResponseModel> = runCatching {
        authDataSource.postSignIn(
            authorization,
            request.toSignInRequestDto()
        ).result.toSignInModel()
    }

    override suspend fun postSignUp(
        authId: String,
        request: SignUpRequestModel
    ): Result<SignUpResponseModel> = runCatching {
        authDataSource.postSignUp(
            authId,
            request.toSignUpRequestDto()
        ).result.toSignUpModel()
    }
}