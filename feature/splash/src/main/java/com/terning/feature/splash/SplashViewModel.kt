package com.terning.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.domain.token.repository.TokenRepository
import com.terning.domain.update.entity.UpdateState
import com.terning.domain.update.usecase.GetUpdateStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val getLatestVersionUseCase: GetUpdateStateUseCase
) : ViewModel() {

    private val _sideEffects = MutableSharedFlow<SplashSideEffect>()
    val sideEffects: SharedFlow<SplashSideEffect> get() = _sideEffects.asSharedFlow()

    private var _updateState: MutableStateFlow<UpdateState> =
        MutableStateFlow(UpdateState.InitialState)
    val updateState = _updateState.asStateFlow()

    private fun getAccessToken(): Boolean = tokenRepository.getAccessToken().isNotBlank()

    suspend fun showSplash() {
        _updateState.value = UpdateState.InitialState
        delay(DELAY_TIME)
    }

    suspend fun checkUpdateState(version: String?) {
        getLatestVersionUseCase(
            version = version,
            callback = { updateState ->
                _updateState.value = updateState
                checkIfUpdateNotAvailable(updateState)
            }
        )
    }

    private fun checkIfUpdateNotAvailable(updateState: UpdateState) {
        if (updateState is UpdateState.NoUpdateAvailable) {
            checkIfAccessTokenAvailable()
        }
    }

    fun checkIfAccessTokenAvailable() = viewModelScope.launch {
        _sideEffects.emit(SplashSideEffect.HasAccessToken(getAccessToken()))
    }

    companion object {
        private const val DELAY_TIME = 2200L
    }
}