package com.terning.feature.intern

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.state.UiState
import com.terning.domain.entity.request.ScrapRequestModel
import com.terning.domain.repository.InternRepository
import com.terning.domain.repository.ScrapRepository
import com.terning.feature.R
import com.terning.feature.intern.model.InternScrapState
import com.terning.feature.intern.model.InternViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InternViewModel @Inject constructor(
    private val internRepository: InternRepository,
    private val scrapRepository: ScrapRepository,
) : ViewModel() {
    private val _internState: MutableStateFlow<InternViewState> =
        MutableStateFlow(InternViewState())
    val internState: StateFlow<InternViewState> = _internState.asStateFlow()

    private val _scrapState: MutableStateFlow<InternScrapState> =
        MutableStateFlow(InternScrapState())

    private val _sideEffect: MutableSharedFlow<InternViewSideEffect> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    fun getInternInfo(id: Long) {
        viewModelScope.launch {
            internRepository.getInternInfo(
                id
            ).onSuccess { internInfo ->
                _internState.update {
                    it.copy(internInfo = UiState.Success(internInfo))
                }
            }.onFailure {
                _sideEffect.emit(
                    InternViewSideEffect.Toast(R.string.server_failure)
                )
            }
        }
    }

    fun postScrap(
        id: Long,
        color: Int,
    ) {
        viewModelScope.launch {
            scrapRepository.postScrap(
                ScrapRequestModel(id, color)
            ).onSuccess {
                _scrapState.update {
                    it.copy(isScrap = UiState.Success(true))
                }
                getInternInfo(id)
            }.onFailure {
                _sideEffect.emit(
                    InternViewSideEffect.Toast(R.string.server_failure)
                )
            }
        }
    }

    fun deleteScrap(
        id: Long,
    ) {
        viewModelScope.launch {
            scrapRepository.deleteScrap(
                ScrapRequestModel(id, null)
            ).onSuccess {
                _scrapState.update {
                    it.copy(isScrap = UiState.Success(false))
                }
                getInternInfo(id)
            }.onFailure {
                _sideEffect.emit(
                    InternViewSideEffect.Toast(R.string.server_failure)
                )
            }
        }
    }

    fun updateSelectColor(newColor: Color) {
        _internState.update {
            it.copy(selectedColor = newColor)
        }
    }

    fun updateScrapDialogVisible(visible: Boolean) {
        _internState.update {
            it.copy(isScrapDialogVisible = visible)
        }
    }

    fun updateScrapped(scrapped: Boolean) {
        _internState.update {
            it.copy(isScrappedState = scrapped)
        }
    }

    fun updateColorChange(change: Boolean) {
        _internState.update {
            it.copy(isColorChange = change)
        }
    }

    fun updateShowWeb(show: Boolean) {
        _internState.update {
            it.copy(showWeb = show)
        }
    }
}