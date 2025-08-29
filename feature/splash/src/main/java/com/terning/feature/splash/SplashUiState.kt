package com.terning.feature.splash

import androidx.compose.runtime.Immutable
import com.terning.domain.update.entity.UpdateState

@Immutable
sealed class SplashUiState {
    data object InitialState : SplashUiState()
    data object NoUpdateAvailable : SplashUiState()
    data class MajorUpdateAvailable(val title: String, val content: String) : SplashUiState()
    data class PatchUpdateAvailable(val title: String, val content: String) : SplashUiState()
}

fun UpdateState.toUi(): SplashUiState = when (this) {
    UpdateState.InitialState -> SplashUiState.InitialState
    UpdateState.NoUpdateAvailable -> SplashUiState.NoUpdateAvailable
    is UpdateState.MajorUpdateAvailable -> SplashUiState.MajorUpdateAvailable(title, content)
    is UpdateState.PatchUpdateAvailable -> SplashUiState.PatchUpdateAvailable(title, content)
}
