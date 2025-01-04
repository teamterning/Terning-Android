package com.terning.feature.onboarding.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.terning.domain.auth.entity.SignInRequest
import com.terning.domain.auth.repository.AuthRepository
import com.terning.domain.token.repository.TokenRepository
import com.terning.feature.onboarding.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.terning.core.designsystem.R as DesignSystemR

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenRepository: TokenRepository,
) : ViewModel() {

    init {
        tokenRepository.clearInfo()
    }

    private val _signInSideEffects = MutableSharedFlow<SignInSideEffect>()
    val signInSideEffects: SharedFlow<SignInSideEffect>
        get() = _signInSideEffects.asSharedFlow()

    fun startKakaoLogIn(isKakaoAvailable: Boolean) {
        viewModelScope.launch {
            if (isKakaoAvailable) _signInSideEffects.emit(SignInSideEffect.StartKakaoTalkLogin)
            else _signInSideEffects.emit(SignInSideEffect.StartKakaoWebLogin)
        }
    }

    fun signInResult(token: OAuthToken?, error: Throwable?) {
        viewModelScope.launch {
            if (error != null) {
                _signInSideEffects.emit(SignInSideEffect.SignInFailure(error))
            } else if (token != null) {
                signInSuccess(token.accessToken)
            }
        }
    }

    fun sigInCancellationOrError(error: Throwable) {
        viewModelScope.launch {
            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                _signInSideEffects.emit(SignInSideEffect.ShowToast(R.string.sign_in_kakao_cancel))
            } else {
                _signInSideEffects.emit(SignInSideEffect.ShowToast(R.string.sign_in_kakao_login_fail))
            }
        }
    }

    private suspend fun signInSuccess(
        accessToken: String,
        authType: String = KAKAO,
    ) {
        authRepository.postSignIn(
            accessToken,
           SignInRequest(authType = authType)
        ).onSuccess { response ->
            when {
                response.accessToken == null -> _signInSideEffects.emit(
                    SignInSideEffect.NavigateSignUp(
                        response.authId,
                    )
                )

                else -> {
                    tokenRepository.setTokens(
                        accessToken = response.accessToken ?: return,
                        refreshToken = response.refreshToken ?: return
                    )
                    tokenRepository.setUserId(response.userId ?: return)

                    _signInSideEffects.emit(SignInSideEffect.NavigateToHome)
                }
            }
        }.onFailure {
            _signInSideEffects.emit(SignInSideEffect.ShowToast(DesignSystemR.string.server_failure))
        }
    }

    companion object {
        private const val KAKAO = "KAKAO"
    }
}
