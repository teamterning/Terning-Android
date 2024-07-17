package com.terning.feature.intern

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.state.UiState
import com.terning.domain.repository.InternRepository
import com.terning.domain.repository.ScrapRepository
import com.terning.feature.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InternViewModel @Inject constructor(
    private val internRepository: InternRepository,
    private val scrapRepository: ScrapRepository,
) : ViewModel() {
    private val _state: MutableStateFlow<InternViewState> =
        MutableStateFlow(InternViewState())
    val state: StateFlow<InternViewState> = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<InternViewSideEffect> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    fun getInternInfo(id: Int) {
        viewModelScope.launch {
            internRepository.getInternInfo(id).onSuccess { internInfo ->
                _state.value = _state.value.copy(
                    internInfo = UiState.Success(internInfo)
                )
            }.onFailure {
                _sideEffect.emit(
                    InternViewSideEffect.Toast(R.string.server_failure)
                )
            }
        }
    }

//    fun postScrap(id: Long, color: Int) {
//        viewModelScope.launch {
//            scrapRepository.postScrap(
//                id,
//                color
//            )
//        }
//    }

    fun updateSelectColor(newColor: Color) {
        _state.value = _state.value.copy(selectedColor = newColor)
    }

    fun updateScrapDialogVisible(visible: Boolean) {
        _state.value = _state.value.copy(isScrapDialogVisible = visible)
    }

    fun updateScrapped(scrapped: Boolean) {
        _state.value = _state.value.copy(isScrappedState = scrapped)
    }

    fun updateColorChange(change: Boolean) {
        _state.value = _state.value.copy(isColorChange = change)
    }

    fun updateShowWeb(show: Boolean) {
        _state.value = _state.value.copy(showWeb = show)
    }
}