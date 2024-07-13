package com.terning.data.repositoryimpl

import com.terning.data.datasource.AuthDataSource
import com.terning.data.dto.request.toSignInRequestDto
import com.terning.domain.entity.request.SignInRequestModel
import com.terning.domain.entity.response.SignInResponseModel
import com.terning.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun postSignIn(
        authorization: String,
        signInRequestModel: SignInRequestModel
    ): Result<SignInResponseModel> = kotlin.runCatching {
        authDataSource.postSignIn(
            authorization,
            signInRequestModel.toSignInRequestDto()
        ).result.toSignInModel()
    }
}