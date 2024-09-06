package com.terning.feature.dialog.detail

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.designsystem.theme.CalBlue1
import com.terning.core.designsystem.theme.CalBlue2
import com.terning.core.designsystem.theme.CalGreen1
import com.terning.core.designsystem.theme.CalGreen2
import com.terning.core.designsystem.theme.CalOrange1
import com.terning.core.designsystem.theme.CalOrange2
import com.terning.core.designsystem.theme.CalPink
import com.terning.core.designsystem.theme.CalPurple
import com.terning.core.designsystem.theme.CalRed
import com.terning.core.designsystem.theme.CalYellow
import com.terning.core.type.ColorType
import com.terning.domain.entity.CalendarScrapRequest
import com.terning.domain.repository.ScrapRepository
import com.terning.feature.R
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
                isColorChangedOnce = false
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
                isColorChanged = colorType != _uiState.value.initialColorType
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
        val colorIndex = getColorIndex(color.main)
        viewModelScope.launch {
            scrapRepository.postScrap(CalendarScrapRequest(id, colorIndex))
                .onSuccess {
                    with(_sideEffect) {
                        emit(ScrapDialogSideEffect.ShowToast(R.string.dialog_scrap_scrapped))
                        emit(ScrapDialogSideEffect.ScrappedAnnouncement)
                    }
                }.onFailure {
                    _sideEffect.emit(ScrapDialogSideEffect.ShowToast(R.string.server_failure))
                }
        }
    }

    fun patchScrap(scrapId: Long, color: ColorType) = viewModelScope.launch {
        val colorIndex = getColorIndex(color.main)
        scrapRepository.patchScrap(CalendarScrapRequest(scrapId, colorIndex))
            .onSuccess {
                _sideEffect.emit(ScrapDialogSideEffect.PatchedScrap)
                _uiState.update { currentState ->
                    currentState.copy(
                        initialColorType = color,
                        isColorChanged = false,
                        isColorChangedOnce = true,
                    )
                }
            }.onFailure {
                _sideEffect.emit(ScrapDialogSideEffect.ShowToast(R.string.server_failure))
            }
    }

    private fun getColorIndex(color: Color): Int = listOf(
        CalRed,
        CalOrange1,
        CalOrange2,
        CalYellow,
        CalGreen1,
        CalGreen2,
        CalBlue1,
        CalBlue2,
        CalPurple,
        CalPink
    ).indexOf(color)
}