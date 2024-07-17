package com.terning.point.di

import android.content.Context
import com.terning.data.local.TerningDataStore
import com.terning.domain.repository.TokenReissueRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenReissueRepository : TokenReissueRepository,
    private val terningDataStore  : TerningDataStore,
    @ApplicationContext private val context : Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("Not yet implemented")
    }

}