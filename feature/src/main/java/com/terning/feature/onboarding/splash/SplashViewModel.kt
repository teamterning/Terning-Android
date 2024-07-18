package com.terning.feature.onboarding.splash

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.terning.domain.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenRepository: TokenRepository,
) : ViewModel() {

    private val _sideEffects = MutableSharedFlow<SplashState>()
    val sideEffects: SharedFlow<SplashState> get() = _sideEffects.asSharedFlow()

    private fun getHasAccessToken(): Boolean = tokenRepository.getAccessToken().isNotBlank()

    fun showSplash(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycleScope.launch {
            delay(DELAY_TIME)
            _sideEffects.emit(SplashState.GetHasAccessToken(getHasAccessToken()))
        }
    }

    companion object {
        private const val DELAY_TIME = 2200L
    }
}