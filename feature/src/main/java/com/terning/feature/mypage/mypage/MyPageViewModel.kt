package com.terning.feature.mypage.mypage

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakewharton.processphoenix.ProcessPhoenix
import com.kakao.sdk.user.UserApiClient
import com.terning.core.state.UiState
import com.terning.domain.repository.MyPageRepository
import com.terning.domain.repository.TokenRepository
import com.terning.feature.R
import com.terning.feature.main.MainActivity
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
class MyPageViewModel @Inject constructor(
    private val myPageRepository: MyPageRepository,
    private val tokenRepository: TokenRepository
) : ViewModel() {

    private val _state: MutableStateFlow<MyPageState> = MutableStateFlow(MyPageState())
    val state: StateFlow<MyPageState> get() = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<MyPageSideEffect>()
    val sideEffects: SharedFlow<MyPageSideEffect> get() = _sideEffects.asSharedFlow()

    fun logoutKakao() {
        UserApiClient.instance.logout { error ->
            if (error == null) {
                postLogout()
            } else {
                _state.value =
                    _state.value.copy(isLogoutAndQuitSuccess = UiState.Failure(error.toString()))
            }
        }
    }

    private fun postLogout() {
        viewModelScope.launch {
            myPageRepository.postLogout().onSuccess {
                tokenRepository.clearInfo()
                _state.value = _state.value.copy(isLogoutAndQuitSuccess = UiState.Success(true))
            }.onFailure {
                _state.value =
                    _state.value.copy(isLogoutAndQuitSuccess = UiState.Failure(it.toString()))
            }
        }
    }

    fun restartApp(context: Context) {
        Handler(Looper.getMainLooper()).post {
            Handler(Looper.getMainLooper()).post {
                ProcessPhoenix.triggerRebirth(
                    context,
                    Intent(context, MainActivity::class.java)
                )
            }
        }
    }

    fun quitKakao() {
        UserApiClient.instance.unlink { error ->
            if (error == null) {
                deleteQuit()
            } else {
                _state.value =
                    _state.value.copy(isLogoutAndQuitSuccess = UiState.Failure(error.toString()))
            }
        }
    }

    private fun deleteQuit() {
        viewModelScope.launch {
            myPageRepository.deleteQuit().onSuccess {
                tokenRepository.clearInfo()
                _state.value = _state.value.copy(isLogoutAndQuitSuccess = UiState.Success(true))
            }.onFailure {
                _state.value =
                    _state.value.copy(isLogoutAndQuitSuccess = UiState.Failure(it.toString()))
            }
        }
    }

    fun getProfile() {
        viewModelScope.launch {
            myPageRepository.getProfile()
                .onSuccess { response ->
                    _state.value = _state.value.copy(
                        isGetSuccess = UiState.Success(true),
                        name = response.name,
                        profileImage = response.profileImage,
                        authType = response.authType
                    )
                }.onFailure {
                    _sideEffects.emit(MyPageSideEffect.ShowToast(R.string.server_failure))
                    _state.value = _state.value.copy(isGetSuccess = UiState.Failure(it.toString()))
                }
        }
    }

    fun fetchShowNotice(show: Boolean) {
        _state.value = _state.value.copy(showNotice = show)
    }

    fun fetchShowOpinion(show: Boolean) {
        _state.value = _state.value.copy(showOpinion = show)
    }

    fun fetchShowService(show: Boolean) {
        _state.value = _state.value.copy(showService = show)
    }

    fun fetchShowPersonal(show: Boolean) {
        _state.value = _state.value.copy(showPersonal = show)
    }

    fun fetchShowLogoutBottomSheet(show: Boolean) {
        _state.value = _state.value.copy(showLogoutBottomSheet = show)
    }

    fun fetchShowQuitBottomSheet(show: Boolean) {
        _state.value = _state.value.copy(showQuitBottomSheet = show)
    }

    fun navigateToNoticeWebView(context: Context) {
        CustomTabsIntent.Builder().build().launchUrl(context, NOTICE_URL.toUri())
    }

    fun navigateToOpinionWebView(context: Context) {
        CustomTabsIntent.Builder().build().launchUrl(context, OPINION_URL.toUri())
    }

    fun navigateToProfileEdit() =
        viewModelScope.launch { _sideEffects.emit(MyPageSideEffect.NavigateToProfileEdit) }

    fun navigateToServiceWebView(context: Context) {
        CustomTabsIntent.Builder().build().launchUrl(context, SERVICE_URL.toUri())
    }

    fun navigateToPersonalWebView(context: Context) {
        CustomTabsIntent.Builder().build().launchUrl(context, PERSONAL_URL.toUri())
    }

    companion object {
        private const val NOTICE_URL =
            "https://abundant-quiver-13f.notion.site/AOS-49b3cc2390ee4dc389e25a5097736944?pvs=4"
        private const val OPINION_URL = "https://forms.gle/AaLpVptfg6cATYWa7"
        private const val SERVICE_URL =
            "https://abundant-quiver-13f.notion.site/69109213e7db4873be6b9600f2f5163a?pvs=4"
        private const val PERSONAL_URL =
            "https://abundant-quiver-13f.notion.site/130cf1915fe7471e9aaf29cab306be3b?pvs=4"
    }
}