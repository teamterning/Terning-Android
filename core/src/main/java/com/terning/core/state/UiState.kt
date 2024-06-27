package com.terning.core.state

sealed interface UiState<out T> {
    data object Empty : UiState<Nothing>

    data object Loading : UiState<Nothing>

    data class Success<T>(
        val data: T,
    ) : UiState<T>

    data class Failure(
        val msg: String,
    ) : UiState<Nothing>
}
