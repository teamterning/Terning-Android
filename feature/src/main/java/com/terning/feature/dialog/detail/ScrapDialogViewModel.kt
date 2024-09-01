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
import com.terning.domain.entity.CalendarScrapRequest
import com.terning.domain.repository.ScrapRepository
import com.terning.feature.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScrapDialogViewModel @Inject constructor(
    private val scrapRepository: ScrapRepository
) : ViewModel() {
    private var _sideEffect: MutableSharedFlow<ScrapDialogSideEffect> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    fun navigateToDetail() = viewModelScope.launch {
        _sideEffect.emit(ScrapDialogSideEffect.NavigateToDetail)
    }

    fun postScrap(id: Long, color: Color) {
        val colorIndex = getColorIndex(color)
        viewModelScope.launch {
            scrapRepository.postScrap(CalendarScrapRequest(id, colorIndex))
                .onSuccess {
                    with(_sideEffect) {
                        emit(ScrapDialogSideEffect.ShowToast(R.string.intern_scrap_add_toast_message))
                        emit(ScrapDialogSideEffect.ScrappedAnnouncement)
                    }
                }.onFailure {
                    _sideEffect.emit(ScrapDialogSideEffect.ShowToast(R.string.server_failure))
                }
        }
    }

    fun patchScrap(scrapId: Long, color: Color) = viewModelScope.launch {
        val colorIndex = getColorIndex(color)
        scrapRepository.patchScrap(CalendarScrapRequest(scrapId, colorIndex))
            .onSuccess {
                _sideEffect.emit(ScrapDialogSideEffect.ChangedColor)
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