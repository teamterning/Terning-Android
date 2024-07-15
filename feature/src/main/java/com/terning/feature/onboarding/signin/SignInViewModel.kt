package com.terning.feature.onboarding.signin

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.terning.domain.entity.request.SignInRequestModel
import com.terning.domain.repository.AuthRepository
import com.terning.domain.repository.TokenRepository
import com.terning.feature.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenRepository: TokenRepository
) : ViewModel() {

    init {
        tokenRepository.clearInfo()
    }

    private val _signInState = MutableStateFlow(SignInState())
    val signInState: StateFlow<SignInState>
        get() = _signInState.asStateFlow()

    private val _signInSideEffects = MutableSharedFlow<SignInSideEffect>()
    val signInSideEffects: SharedFlow<SignInSideEffect>
        get() = _signInSideEffects.asSharedFlow()

    fun startKakaoLogIn(context: Context) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                signInResult(context, token, error)
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                signInResult(context, token, error)
            }
        }
    }

    private fun signInResult(context: Context, token: OAuthToken?, error: Throwable?) {
        viewModelScope.launch {
            if (error != null) {
                signInFailure(context, error)
            } else if (token != null) {
                signInSuccess(token.accessToken)
            }
        }
    }

    private fun signInFailure(context: Context, error: Throwable?) {
        if (error.toString().contains(KAKAO_NOT_LOGGED_IN)) {
            UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                signInResult(context, token, error)
            }
        } else {
            sigInCancellationOrError(error)
        }
    }

    private fun sigInCancellationOrError(error: Throwable?) {
        viewModelScope.launch {
            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                _signInSideEffects.emit(SignInSideEffect.ShowToast(R.string.sign_in_kakao_cancel))
            } else {
                _signInSideEffects.emit(SignInSideEffect.ShowToast(R.string.sign_in_kakao_login_fail))
            }
        }
    }

    private fun signInSuccess(
        accessToken: String,
        platform: String = KAKAO
    ) {
        viewModelScope.launch {
            authRepository.postSignIn(
                accessToken,
                SignInRequestModel(platform)
            ).onSuccess { response ->
                tokenRepository.setTokens(response.accessToken, response.refreshToken)
                tokenRepository.setUserId(response.userId)

                if (response.accessToken == null) _signInSideEffects.emit(SignInSideEffect.NavigateSignUp)
                else _signInSideEffects.emit(SignInSideEffect.NavigateToHome)
            }.onFailure {
                _signInSideEffects.emit(SignInSideEffect.ShowToast(R.string.server_failure))
            }
        }
    }

    companion object {
        private const val KAKAO_NOT_LOGGED_IN = "statusCode=302"
        private const val KAKAO = "KAKAO"
    }
}
