package com.terning.feature.onboarding.signin

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.terning.core.state.UiState
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
class SignInViewModel @Inject constructor() : ViewModel() {

    private val _signInState = MutableStateFlow(SignInState())
    val signInState: StateFlow<SignInState>
        get() = _signInState.asStateFlow()

    private val _signInSideEffects = MutableSharedFlow<SignInSideEffect>()
    val signInSideEffects: SharedFlow<SignInSideEffect>
        get() = _signInSideEffects.asSharedFlow()

    fun startKakaoLogIn(context: Context) {
        // 카카오톡 실행 가능 여부 확인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            // 카카오톡 로그인 시도
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                signInResult(context, token, error)
            }
        } else {
            // 카카오톡 앱이 없는 경우에 웹 로그인 활용
            UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                signInResult(context, token, error)
            }
        }
    }

    private fun signInResult(context: Context, token: OAuthToken?, error: Throwable?) {
        viewModelScope.launch {
            if (error != null) {
                if (error.toString().contains("statusCode=302")) {
                    UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                        signInResult(context, token, error)
                        // 성공 후에 넘어가게
                    }
                } else {
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        // 로그인 취소 처리
                        _signInSideEffects.emit(SignInSideEffect.ShowToast("로그인 취소"))
                    } else {
                        // 로그인 실패 처리
                        _signInSideEffects.emit(SignInSideEffect.ShowToast("로그인 실패  ${error.localizedMessage}"))
                        Log.d("kakao", error.toString())
                    }
                }
            } else if (token != null) {
                // 로그인 성공 처리 (액세스 토큰 저장 및 메인 화면으로 이동)
                _signInState.value =
                    _signInState.value.copy(accessToken = UiState.Success(token.accessToken))
                _signInSideEffects.emit(SignInSideEffect.NavigateToMain)
            }
        }
    }
}