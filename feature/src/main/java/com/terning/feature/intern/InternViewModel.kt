package com.terning.feature.intern

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.terning.feature.intern.model.InternScrapState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class InternViewModel @Inject constructor() : ViewModel() {
    private val _state: MutableStateFlow<InternScrapState> =
        MutableStateFlow(InternScrapState())

    val state: StateFlow<InternScrapState> get() = _state

    fun updateScrap(newScrap: Boolean) {
        _state.value = _state.value.copy(isScrap = newScrap)
    }

    fun updateSelectColor(newColor: Color) {
        _state.value = _state.value.copy(selectedColor = newColor)
    }

    fun updateScrapDialogVisible(visible: Boolean) {
        _state.value = _state.value.copy(isScrapDialogVisible = visible)
    }

    fun updateScrapped(scrapped: Boolean) {
        _state.value = _state.value.copy(isScrapped = scrapped)
    }

    fun updateColorChange(change: Boolean) {
        _state.value = _state.value.copy(isColorChange = change)
    }

}