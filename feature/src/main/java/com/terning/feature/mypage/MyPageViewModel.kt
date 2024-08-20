package com.terning.feature.mypage

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
import com.terning.feature.main.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val myPageRepository: MyPageRepository,
    private val tokenRepository: TokenRepository
) : ViewModel() {

    init {
        getProfile()
    }

    private val _state: MutableStateFlow<MyPageState> = MutableStateFlow(MyPageState())
    val state: StateFlow<MyPageState> get() = _state.asStateFlow()

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

    private fun getProfile() {
        viewModelScope.launch {
            myPageRepository.getProfile()
                .onSuccess { response ->
                _state.value = _state.value.copy(
                    isGetSuccess = UiState.Success(true),
                    name = response.name,
                    authType = response.authType
                )
            }.onFailure {
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

    fun fetchShowLogoutBottomSheet(show: Boolean) {
        _state.value = _state.value.copy(showLogoutBottomSheet = show)
    }

    fun fetchShowQuitBottomSheet(show: Boolean) {
        _state.value = _state.value.copy(showQuitBottomSheet = show)
    }

    fun navigateToNoticeWebView(context: Context) {
        val url = NOTICE_URL.toUri()
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(context, url)
    }

    fun navigateToOpinionWebView(context: Context) {
        val url = OPINION_URL.toUri()
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(context, url)
    }

    companion object {
        private const val NOTICE_URL =
            "https://abundant-quiver-13f.notion.site/69109213e7db4873be6b9600f2f5163a"
        private const val OPINION_URL = "https://forms.gle/AaLpVptfg6cATYWa7"
    }
}