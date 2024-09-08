package com.terning.feature.filtering.starthome

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StartHomeViewModel : ViewModel() {
    private val _state: MutableStateFlow<StartHomeState> =
        MutableStateFlow(StartHomeState())
    val state: StateFlow<StartHomeState> get() = _state.asStateFlow()

    fun updateButtonState() {
        _state.value = _state.value.copy(isButtonVisible = true)
    }
}