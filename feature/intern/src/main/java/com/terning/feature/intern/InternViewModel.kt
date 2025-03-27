package com.terning.feature.intern

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.designsystem.state.UiState
import com.terning.domain.intern.repository.InternRepository
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
) : ViewModel() {

    private val _internUiState = MutableStateFlow(InternUiState())
    val internUiState get() = _internUiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<InternViewSideEffect> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    fun getInternInfo(id: Long) {
        viewModelScope.launch {
            internRepository.getInternInfo(id)
                .onSuccess { internInfoModel ->
                    _internUiState.value = _internUiState.value.copy(
                        loadState = UiState.Success(internInfoModel)
                    )
                }
                .onFailure { exception: Throwable ->
                    _internUiState.value = _internUiState.value.copy(
                        loadState = UiState.Failure(exception.toString())
                    )
                    _sideEffect.emit(InternViewSideEffect.Toast(R.string.server_failure))
                }
        }
    }

    fun updateScrapCancelDialogVisibility(visibility: Boolean) {
        _internUiState.update { currentState ->
            currentState.copy(
                scrapCancelDialogVisibility = visibility,
            )
        }
    }

    fun updateInternDialogVisibility(visibility: Boolean) {
        _internUiState.update { currentState ->
            currentState.copy(
                internDialogVisibility = visibility
            )
        }
    }

    fun updateShowWeb(show: Boolean) {
        _internUiState.update {
            it.copy(showWeb = show)
        }
    }
}
