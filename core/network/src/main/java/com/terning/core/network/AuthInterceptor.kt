package com.terning.core.network

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.jakewharton.processphoenix.ProcessPhoenix
import com.terning.core.local.TerningDataStore
import com.terning.domain.tokenreissue.repository.TokenReissueRepository
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

        Timber.d("GET ACCESS TOKEN : ${terningDataStore.accessToken}")

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
                            accessToken = data.accessToken
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

                restartApp(MESSAGE_TOKEN_EXPIRED)
            }

            CODE_MANY_REQUESTS -> {
                restartApp(MESSAGE_TOO_MANY_REQUESTS)
            }
        }
        return response
    }

    private fun Request.Builder.newAuthBuilder() =
        this.addHeader(AUTHORIZATION, "$BEARER ${terningDataStore.accessToken}")

    private fun restartApp(message: String) {
        terningDataStore.clearInfo()

        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            ProcessPhoenix.triggerRebirth(context)
        }
    }

    companion object {
        private const val CODE_TOKEN_EXPIRED = 401
        private const val CODE_MANY_REQUESTS = 429
        private const val MESSAGE_TOKEN_EXPIRED = "토큰이 만료되었어요\n다시 로그인 해주세요"
        private const val MESSAGE_TOO_MANY_REQUESTS = "요청 횟수가 너무 많아요\n잠시 후 다시 시도해 주세요"
        private const val BEARER = "Bearer"
        private const val AUTHORIZATION = "Authorization"
    }
}