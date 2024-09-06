package com.terning.feature.intern

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.state.UiState
import com.terning.domain.entity.calendar.CalendarScrapRequest
import com.terning.domain.repository.InternRepository
import com.terning.domain.repository.ScrapRepository
import com.terning.feature.R
import com.terning.feature.intern.model.InternScrapState
import com.terning.feature.intern.model.InternUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
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
    private val _internUiState = MutableStateFlow(InternUiState())
    val internUiState get() = _internUiState.asStateFlow()

    private val _scrapState: MutableStateFlow<InternScrapState> =
        MutableStateFlow(InternScrapState())

    private val _sideEffect: MutableSharedFlow<InternViewSideEffect> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    fun getInternInfo(id: Long) {
        viewModelScope.launch {
            internRepository.getInternInfo(id)
                .onSuccess { internInfoModel ->
                    _internUiState.update { currentState ->
                        currentState.copy(loadState = UiState.Success(internInfoModel))
                    }
                }
                .onFailure {
                    _sideEffect.emit(InternViewSideEffect.Toast(R.string.server_failure))
                }
        }
    }


    fun postScrap(
        id: Long,
        color: Int,
    ) {
        viewModelScope.launch {
            scrapRepository.postScrap(
                CalendarScrapRequest(id, color)
            ).onSuccess {
                _scrapState.update {
                    it.copy(isScrap = UiState.Success(true))
                }
                getInternInfo(id)
                updateScrapDialogVisible(false)
                _sideEffect.emit(
                    InternViewSideEffect.Toast(R.string.intern_scrap_add_toast_message)
                )
            }.onFailure {
                _sideEffect.emit(
                    InternViewSideEffect.Toast(R.string.server_failure)
                )
            }
        }
    }

    fun deleteScrap(
        scrapId: Long?,
        announcementId: Long,
    ) {
        viewModelScope.launch {
            scrapId?.let { CalendarScrapRequest(it, null) }?.let { scrapRequestModel ->
                scrapRepository.deleteScrap(
                    scrapRequestModel
                ).onSuccess {
                    _scrapState.update {
                        it.copy(isScrap = UiState.Success(false))
                    }
                    getInternInfo(announcementId)
                    updateScrapDialogVisible(false)
                    _sideEffect.emit(InternViewSideEffect.Toast(R.string.intern_scrap_delete_toast_message))
                }.onFailure {
                    _sideEffect.emit(
                        InternViewSideEffect.Toast(R.string.server_failure)
                    )
                }
            }
        }
    }

    fun updateSelectColor(newColor: Color) {
        _internUiState.update {
            it.copy(selectedColor = newColor)
        }
    }

    fun updateScrapDialogVisible(visible: Boolean) {
        _internUiState.update {
            it.copy(isScrapDialogVisible = visible)
        }
    }

    fun updatePaletteOpen(open: Boolean) {
        _internUiState.update {
            it.copy(isPaletteOpen = open)
        }
    }

    fun updateColorChange(change: Boolean) {
        _internUiState.update {
            it.copy(isColorChange = change)
        }
    }

    fun updateShowWeb(show: Boolean) {
        _internUiState.update {
            it.copy(showWeb = show)
        }
    }
}