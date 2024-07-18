package com.terning.point.di

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.jakewharton.processphoenix.ProcessPhoenix
import com.terning.core.extension.stringToast
import com.terning.data.local.TerningDataStore
import com.terning.domain.repository.TokenReissueRepository
import com.terning.feature.main.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenReissueRepository: TokenReissueRepository,
    private val terningDataStore: TerningDataStore,
    @ApplicationContext private val context: Context,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        Timber.d("GET REFRESH TOKEN : ${terningDataStore.refreshToken}")

        val authRequest = if (terningDataStore.accessToken.isNotBlank()) {
            originalRequest.newBuilder().newAuthBuilder().build()
        } else {
            originalRequest
        }

        val response = chain.proceed(authRequest)

        when (response.code) {
            CODE_TOKEN_EXPIRED -> {
                try {
                    runBlocking {
                        tokenReissueRepository.postReissueToken(
                            terningDataStore.refreshToken
                        )
                    }.onSuccess { data ->
                        terningDataStore.apply {
                            refreshToken = data.refreshToken
                        }

                        response.close()

                        val newRequest =
                            authRequest.newBuilder().removeHeader(AUTHORIZATION).newAuthBuilder()
                                .build()

                        return chain.proceed(newRequest)
                    }
                } catch (t: Throwable) {
                    Timber.d(t.message)
                }

                terningDataStore.clearInfo()

                Handler(Looper.getMainLooper()).post {
                    context.stringToast(TOKEN_EXPIRED_ERROR)
                    Handler(Looper.getMainLooper()).post {
                        ProcessPhoenix.triggerRebirth(
                            context,
                            Intent(context, MainActivity::class.java)
                        )
                    }
                }
            }
        }
        return response
    }

    private fun Request.Builder.newAuthBuilder() =
        this.addHeader(AUTHORIZATION, "$BEARER ${terningDataStore.refreshToken}")

    companion object {
        private const val CODE_TOKEN_EXPIRED = 401
        private const val TOKEN_EXPIRED_ERROR = "토큰이 만료되었어요\n다시 로그인 해주세요"
        private const val BEARER = "Bearer"
        private const val AUTHORIZATION = "Authorization"
    }
}