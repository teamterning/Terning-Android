package com.terning.feature.filtering.startfiltering

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StartFilteringViewModel : ViewModel() {

    private val _state: MutableStateFlow<StartFilteringState> =
        MutableStateFlow(StartFilteringState())
    val state: StateFlow<StartFilteringState> get() = _state.asStateFlow()

    fun updateButtonState() {
        _state.value = _state.value.copy(isButtonVisible = true)
    }

}