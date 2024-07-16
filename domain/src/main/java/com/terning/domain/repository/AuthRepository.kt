package com.terning.domain.repository

import com.terning.domain.entity.request.SignInRequestModel
import com.terning.domain.entity.request.SignUpRequestModel
import com.terning.domain.entity.response.SignInResponseModel
import com.terning.domain.entity.response.SignUpResponseModel

interface AuthRepository {

    suspend fun postSignIn(
        authorization: String,
        request: SignInRequestModel
    ): Result<SignInResponseModel>

    suspend fun postSignUp(
        userId: Long,
        request: SignUpRequestModel
    ): Result<SignUpResponseModel>
}