package com.terning.feature.search.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.state.UiState
import com.terning.domain.entity.response.SearchViewsResponseModel
import com.terning.domain.repository.SearchViewsRepository
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
class SearchViewModel @Inject constructor(
    private val searchViewsRepository: SearchViewsRepository,
) : ViewModel() {
    private val _state: MutableStateFlow<SearchViewsState> = MutableStateFlow(SearchViewsState())
    val state: StateFlow<SearchViewsState> = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SearchViewsSideEffect> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        getSearchViews()
    }

    fun getSearchViews() {
        viewModelScope.launch {
            searchViewsRepository.getSearchViewsList().onSuccess { response ->
                val searchViewsList = response.map { entity ->
                    SearchViewsResponseModel(
                        title = entity.title,
                        companyImage = entity.companyImage,
                        announcementId = entity.announcementId
                    )
                }
                _state.value = _state.value.copy(
                    searchViewsList = UiState.Success(searchViewsList)
                )
                _sideEffect.emit(SearchViewsSideEffect.Toast(R.string.server_success))
            }.onFailure {
                _sideEffect.emit(SearchViewsSideEffect.Toast(R.string.server_failure))
            }
        }
    }
}