package com.terning.domain.repository

import com.terning.domain.entity.request.SignInRequestModel
import com.terning.domain.entity.response.SignInResponseModel

interface AuthRepository {
    suspend fun postSignIn(
        authorization: String,
        request: SignInRequestModel
    ): Result<SignInResponseModel>
}