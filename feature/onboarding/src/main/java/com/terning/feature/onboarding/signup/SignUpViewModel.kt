package com.terning.feature.onboarding.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.domain.auth.entity.SignUpRequest
import com.terning.domain.auth.repository.AuthRepository
import com.terning.domain.token.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import com.terning.core.designsystem.R as DesignSystemR

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenRepository: TokenRepository
) : ViewModel() {

    private val _state: MutableStateFlow<SignUpState> = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> get() = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<SignUpSideEffect>()
    val sideEffects: SharedFlow<SignUpSideEffect> get() = _sideEffects.asSharedFlow()

    fun updateButtonValidation(isValid: Boolean) {
        _state.value = _state.value.copy(isButtonValid = isValid)
    }

    fun updateName(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    fun updateProfileImage(profileImage: String) {
        _state.value = _state.value.copy(profileImage = profileImage)
    }

    fun updateAuthId(authId: String) {
        _state.value = _state.value.copy(authId = authId)
    }

    fun fetchAndSaveFcmToken() {
        viewModelScope.launch {
            tokenRepository.fetchAndSetFcmToken()
                .onSuccess {
                    signUpUser()
                }.onFailure(Timber::e)
        }
    }

    private fun signUpUser() {
        viewModelScope.launch {
            authRepository.signUp(
                state.value.authId,
                state.value.run {
                    SignUpRequest(
                        name = name,
                        profileImage = profileImage,
                        authType = KAKA0,
                        fcmToken = tokenRepository.getFcmToken()
                    )
                }
            ).onSuccess { response ->
                tokenRepository.apply {
                    setAccessToken(response.accessToken)
                    setRefreshToken(response.refreshToken)
                    setUserId(response.userId)
                }
                _sideEffects.emit(SignUpSideEffect.NavigateToStartFiltering)
            }.onFailure {
                _sideEffects.emit(SignUpSideEffect.ShowToast(DesignSystemR.string.server_failure))
            }
        }
    }

    fun updateBottomSheet(isVisible: Boolean) {
        _state.value = _state.value.copy(showBottomSheet = isVisible)
    }

    companion object {
        private const val KAKA0 = "KAKAO"
    }
}