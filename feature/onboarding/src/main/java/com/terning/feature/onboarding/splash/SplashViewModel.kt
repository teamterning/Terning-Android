package com.terning.feature.onboarding.splash

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.terning.domain.token.repository.TokenRepository
import com.terning.feature.onboarding.signup.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenRepository: TokenRepository,
) : ViewModel() {

    private val _sideEffects = MutableSharedFlow<SplashSideEffect>()
    val sideEffects: SharedFlow<SplashSideEffect> get() = _sideEffects.asSharedFlow()

    private val _state: MutableStateFlow<SplashState> = MutableStateFlow(SplashState())
    val state: StateFlow<SplashState> get() = _state.asStateFlow()

    private fun getAccessToken(): Boolean = tokenRepository.getAccessToken().isNotBlank()

    fun showSplash(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycleScope.launch {
            delay(DELAY_TIME)
            _sideEffects.emit(SplashSideEffect.HasAccessToken(hasAccessToken = getAccessToken()))
        }
    }

    fun updateNewRoute(newRoute: String){
        _state.update {
            it.copy(
                newRoute = newRoute
            )
        }
    }

    companion object {
        private const val DELAY_TIME = 2200L
    }
}