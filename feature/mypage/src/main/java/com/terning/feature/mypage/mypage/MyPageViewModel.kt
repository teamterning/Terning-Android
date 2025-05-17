package com.terning.feature.mypage.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.user.UserApiClient
import com.terning.core.designsystem.extension.groupBy
import com.terning.core.designsystem.state.UiState
import com.terning.core.designsystem.type.AlarmType.DISABLED
import com.terning.core.designsystem.type.AlarmType.ENABLED
import com.terning.domain.mypage.entity.AlarmStatus
import com.terning.domain.mypage.repository.MyPageRepository
import com.terning.domain.user.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.terning.core.designsystem.R as DesignSystemR

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val myPageRepository: MyPageRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state: MutableStateFlow<MyPageState> = MutableStateFlow(MyPageState())
    val state: StateFlow<MyPageState> get() = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<MyPageSideEffect>()
    val sideEffects: SharedFlow<MyPageSideEffect> get() = _sideEffects.asSharedFlow()

    private val debounceFlow = MutableSharedFlow<AlarmInfo>()

    private val lastSuccessfulAlarmStatus = mutableMapOf<String, Boolean>()

    init {
        viewModelScope.launch {
            debounceFlow
                .groupBy { it.id }
                .flatMapMerge { (_, flow) -> flow.debounce(300L) }
                .collect { info ->
                    val result = myPageRepository.updateAlarmState(
                        AlarmStatus(if (info.isAlarmAvailable) ENABLED.value else DISABLED.value)
                    )

                    if (result.isSuccess) {
                        lastSuccessfulAlarmStatus[info.id] = info.isAlarmAvailable
                    } else {
                        val previous = lastSuccessfulAlarmStatus[info.id] ?: !info.isAlarmAvailable
                        _state.update {
                            it.copy(pushStatus = if (previous) ENABLED.value else DISABLED.value)
                        }
                        userRepository.setAlarmAvailable(previous)

                        _sideEffects.emit(MyPageSideEffect.ShowToast(DesignSystemR.string.server_failure))
                    }
                }
        }
    }

    fun logoutKakao() {
        UserApiClient.instance.logout { error ->
            if (error == null) {
                logoutApp()
            } else {
                viewModelScope.launch {
                    _sideEffects.emit(MyPageSideEffect.ShowToast(DesignSystemR.string.server_failure))
                }
            }
        }
    }

    private fun logoutApp() {
        viewModelScope.launch {
            myPageRepository.postLogout().onSuccess {
                userRepository.clearInfo()
                _sideEffects.emit(MyPageSideEffect.RestartApp)
            }.onFailure {
                _sideEffects.emit(MyPageSideEffect.ShowToast(DesignSystemR.string.server_failure))
            }
        }
    }

    fun quitKakao() {
        UserApiClient.instance.unlink { error ->
            if (error == null) {
                quitApp()
            } else {
                viewModelScope.launch {
                    _sideEffects.emit(MyPageSideEffect.ShowToast(DesignSystemR.string.server_failure))
                }
            }
        }
    }

    private fun quitApp() {
        viewModelScope.launch {
            myPageRepository.deleteQuit().onSuccess {
                userRepository.clearInfo()
                _sideEffects.emit(MyPageSideEffect.RestartApp)
            }.onFailure {
                _sideEffects.emit(MyPageSideEffect.ShowToast(DesignSystemR.string.server_failure))
            }
        }
    }

    fun getProfile() {
        viewModelScope.launch {
            myPageRepository.getProfile()
                .onSuccess { response ->
                    _state.update { currentState ->
                        currentState.copy(
                            isGetSuccess = UiState.Success(true),
                            name = response.name,
                            profileImage = response.profileImage,
                            authType = response.authType,
                            pushStatus = response.pushStatus
                        )
                    }
                }.onFailure {
                    _sideEffects.emit(MyPageSideEffect.ShowToast(DesignSystemR.string.server_failure))
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

    fun updateAlarmAvailability(availability: Boolean) {
        _state.update { state ->
            state.copy(pushStatus = if (availability) ENABLED.value else DISABLED.value)
        }

        userRepository.setAlarmAvailable(availability)

        viewModelScope.launch {
            debounceFlow.emit(AlarmInfo(id = "user", isAlarmAvailable = availability))
        }
    }

    fun getAlarmAvailability(): Boolean = userRepository.getAlarmAvailable()

    fun updateDialogVisibility(visibility: Boolean) {
        _state.update { currentState ->
            currentState.copy(
                showAlarmDialog = visibility
            )
        }
    }
}