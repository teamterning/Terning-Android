package com.terning.feature.mypage.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.user.UserApiClient
import com.terning.core.state.UiState
import com.terning.domain.repository.MyPageRepository
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
                logoutApp()
            } else {
                viewModelScope.launch {
                    _sideEffects.emit(MyPageSideEffect.ShowToast(R.string.server_failure))
                }
            }
        }
    }

    private fun logoutApp() {
        viewModelScope.launch {
            myPageRepository.postLogout().onSuccess {
                tokenRepository.clearInfo()
                _sideEffects.emit(MyPageSideEffect.RestartApp)
            }.onFailure {
                _sideEffects.emit(MyPageSideEffect.ShowToast(R.string.server_failure))
            }
        }
    }

    fun quitKakao() {
        UserApiClient.instance.unlink { error ->
            if (error == null) {
                quitApp()
            } else {
                viewModelScope.launch {
                    _sideEffects.emit(MyPageSideEffect.ShowToast(R.string.server_failure))
                }
            }
        }
    }

    private fun quitApp() {
        viewModelScope.launch {
            myPageRepository.deleteQuit().onSuccess {
                tokenRepository.clearInfo()
                _sideEffects.emit(MyPageSideEffect.RestartApp)
            }.onFailure {
                _sideEffects.emit(MyPageSideEffect.ShowToast(R.string.server_failure))
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
        viewModelScope.launch {
            _sideEffects.emit(MyPageSideEffect.NavigateToNoticeWebView)
            _state.value = _state.value.copy(showNotice = show)
        }
    }

    fun fetchShowOpinion(show: Boolean) {
        viewModelScope.launch {
            _sideEffects.emit(MyPageSideEffect.NavigateToOpinionWebView)
            _state.value = _state.value.copy(showOpinion = show)
        }
    }

    fun fetchShowService(show: Boolean) {
        viewModelScope.launch {
            _sideEffects.emit(MyPageSideEffect.NavigateToServiceWebView)
            _state.value = _state.value.copy(showService = show)
        }
    }

    fun fetchShowPersonal(show: Boolean) {
        viewModelScope.launch {
            _sideEffects.emit(MyPageSideEffect.NavigateToPersonalWebView)
            _state.value = _state.value.copy(showPersonal = show)
        }
    }

    fun fetchShowLogoutBottomSheet(show: Boolean) {
        _state.value = _state.value.copy(showLogoutBottomSheet = show)
    }

    fun fetchShowQuitBottomSheet(show: Boolean) {
        _state.value = _state.value.copy(showQuitBottomSheet = show)
    }

    fun navigateToProfileEdit() =
        viewModelScope.launch { _sideEffects.emit(MyPageSideEffect.NavigateToProfileEdit) }

}