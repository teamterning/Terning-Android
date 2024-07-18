package com.terning.feature.onboarding.splash

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.terning.domain.repository.TokenReissueRepository
import com.terning.domain.repository.TokenRepository
import com.terning.feature.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val tokenReissueRepository: TokenReissueRepository
) : ViewModel() {

    private val _state: MutableStateFlow<SplashState> = MutableStateFlow(SplashState())
    val state: StateFlow<SplashState> get() = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<SplashSideEffect>()
    val sideEffects: SharedFlow<SplashSideEffect> get() = _sideEffects.asSharedFlow()

    fun showSplash(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycleScope.launch {
            delay(DELAY_TIME)
            _sideEffects.emit(SplashSideEffect.GetHasRefreshToken(getHasRefreshToken()))
        }
    }

    private fun getHasRefreshToken(): Boolean = tokenRepository.getRefreshToken().isNotBlank()

    fun postTokenReissue() {
        viewModelScope.launch {
            tokenReissueRepository.postReissueToken(
                tokenRepository.getRefreshToken(),
            ).onSuccess { response ->
                tokenRepository.setTokens(
                    accessToken = response.accessToken,
                    refreshToken = response.refreshToken
                )
                _sideEffects.emit(SplashSideEffect.NavigateToHome)
            }.onFailure {
                _sideEffects.emit(SplashSideEffect.ShowToast(R.string.server_failure))
            }
        }
    }

    companion object {
        private const val DELAY_TIME = 2200L
    }
}