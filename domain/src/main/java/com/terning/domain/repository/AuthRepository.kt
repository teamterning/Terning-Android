package com.terning.domain.repository

import com.terning.domain.entity.auth.SignInRequestModel
import com.terning.domain.entity.auth.SignUpRequestModel
import com.terning.domain.entity.auth.SignInResponseModel
import com.terning.domain.entity.auth.SignUpResponseModel

interface AuthRepository {

    suspend fun postSignIn(
        authorization: String,
        request: SignInRequestModel
    ): Result<SignInResponseModel>

    suspend fun postSignUp(
        authId: String,
        request: SignUpRequestModel
    ): Result<SignUpResponseModel>

}