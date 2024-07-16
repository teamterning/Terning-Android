package com.terning.feature.search.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.state.UiState
import com.terning.domain.repository.SearchRepository
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
    private val searchRepository: SearchRepository,
) : ViewModel() {
    private val _viewState: MutableStateFlow<SearchState> = MutableStateFlow(SearchState())
    val viewState: StateFlow<SearchState> = _viewState.asStateFlow()

    private val _scrapState: MutableStateFlow<SearchState> = MutableStateFlow(SearchState())
    val scrapState: StateFlow<SearchState> = _scrapState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SearchSideEffect> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        getSearchViews()
        getSearchScraps()
    }

    fun getSearchViews() {
        viewModelScope.launch {
            searchRepository.getSearchViewsList().onSuccess { searchViewsList ->
                _viewState.value = _viewState.value.copy(
                    searchViewsList = UiState.Success(searchViewsList)
                )
            }.onFailure {
                _sideEffect.emit(SearchSideEffect.Toast(R.string.server_failure))
            }
        }
    }

    fun getSearchScraps() {
        viewModelScope.launch {
            searchRepository.getSearchScrapsList().onSuccess { searchScrapsList ->
                _scrapState.value = _scrapState.value.copy(
                    searchScrapsList = UiState.Success(searchScrapsList)
                )
            }.onFailure {
                _sideEffect.emit(SearchSideEffect.Toast(R.string.server_failure))
            }
        }
    }
}