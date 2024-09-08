package com.terning.feature.onboarding.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.domain.entity.onboarding.SignUpRequest
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

    fun postSignUpWithServer() {
        viewModelScope.launch {
            authRepository.postSignUp(
                state.value.authId,
                state.value.run {
                    SignUpRequest(
                        name = name,
                        profileImage = profileImage,
                        authType = KAKA0
                    )
                }
            ).onSuccess { response ->
                tokenRepository.setTokens(
                    accessToken = response.accessToken,
                    refreshToken = response.refreshToken
                )
                tokenRepository.setUserId(response.userId)

                _sideEffects.emit(SignUpSideEffect.NavigateToStartFiltering)
            }.onFailure {
                _sideEffects.emit(SignUpSideEffect.ShowToast(R.string.server_failure))
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