package com.terning.feature.dialog.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.designsystem.R.string.server_failure
import com.terning.core.designsystem.type.ColorType
import com.terning.domain.scrap.entity.CalendarScrapRequest
import com.terning.domain.scrap.repository.ScrapRepository
import com.terning.featrue.dialog.R
import com.terning.feature.dialog.detail.state.ScrapDialogUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScrapDialogViewModel @Inject constructor(
    private val scrapRepository: ScrapRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow(ScrapDialogUiState())
    val uiState = _uiState.asStateFlow()

    private var _sideEffect: MutableSharedFlow<ScrapDialogSideEffect> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    fun initUiState() {
        _uiState.update { currentState ->
            currentState.copy(
                isColorChanged = false,
            )
        }
    }

    fun setInitialAndSelectedColorType(colorType: ColorType) {
        _uiState.update { currentState ->
            currentState.copy(
                initialColorType = colorType,
                selectedColorType = colorType
            )
        }
    }

    fun changeSelectedColor(colorType: ColorType) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedColorType = colorType,
                isColorChanged = true
            )
        }
    }

    fun navigateToDetail() = viewModelScope.launch {
        with(_sideEffect) {
            emit(ScrapDialogSideEffect.NavigateToDetail)
            emit(ScrapDialogSideEffect.DismissDialog)
        }
    }

    fun navigateUp() = viewModelScope.launch {
        _sideEffect.emit(ScrapDialogSideEffect.DismissDialog)
    }

    fun postScrap(id: Long, color: ColorType) {
        viewModelScope.launch {
            scrapRepository.postScrap(CalendarScrapRequest(id, color.typeName))
                .onSuccess {
                    with(_sideEffect) {
                        emit(ScrapDialogSideEffect.ShowToast(R.string.dialog_scrap_scrapped))
                        emit(ScrapDialogSideEffect.ScrappedAnnouncement)
                    }
                }.onFailure {
                    _sideEffect.emit(ScrapDialogSideEffect.ShowToast(server_failure))
                }
        }
    }

    fun patchScrap(scrapId: Long, color: ColorType) = viewModelScope.launch {
        scrapRepository.patchScrap(CalendarScrapRequest(scrapId, color.typeName))
            .onSuccess {
                _sideEffect.emit(ScrapDialogSideEffect.PatchedScrap)
                _uiState.update { currentState ->
                    currentState.copy(
                        initialColorType = color,
                        isColorChanged = false,
                    )
                }
            }.onFailure {
                _sideEffect.emit(ScrapDialogSideEffect.ShowToast(server_failure))
            }
    }
}