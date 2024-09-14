package com.terning.feature.search.searchprocess

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.domain.entity.search.SearchResult
import com.terning.domain.repository.SearchRepository
import com.terning.feature.R
import com.terning.feature.search.searchprocess.models.SearchProcessState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchProcessViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(SearchProcessState())
    val state: StateFlow<SearchProcessState> = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SearchProcessSideEffect>()
    val sideEffect: SharedFlow<SearchProcessSideEffect> = _sideEffect.asSharedFlow()

    private val _internSearchResultData = MutableStateFlow<List<SearchResult>>(emptyList())
    val internSearchResultData: StateFlow<List<SearchResult>> =
        _internSearchResultData.asStateFlow()

    fun getSearchList(
        keyword: String,
        sortBy: String = SORT_BY,
        page: Int,
        size: Int,
    ) {
        viewModelScope.launch {
            searchRepository.getSearchList(keyword, sortBy, page, size)
                .onSuccess { results ->
                    _internSearchResultData.value = results
                }
                .onFailure {
                    _sideEffect.emit(SearchProcessSideEffect.Toast(R.string.server_failure))
                }
        }
    }

    fun updateText(newText: String) {
        _state.update { it.copy(text = newText) }
    }

    fun updateQuery(query: String) {
        _state.update { it.copy(keyword = query) }
    }

    fun updateShowSearchResults(show: Boolean) {
        _state.update { it.copy(showSearchResults = show, existSearchResults = true) }
    }

    fun updateExistSearchResults(query: String) {
        val exists =
            _internSearchResultData.value.any { it.title.contains(query, ignoreCase = true) }
        _state.update { it.copy(existSearchResults = exists) }
    }

    fun updateScrapDialogVisible(visible: Boolean) {
        _state.update { it.copy(isScrapDialogVisible = visible) }
    }

    fun updateScrapped(scrapped: Boolean) {
        _state.update { it.copy(scrapped = scrapped) }
    }

    fun updateSelectedInternIndex(index: Long) {
        _state.update { it.copy(selectedInternIndex = index.toInt()) }
    }

    fun toggleSheetState() {
        _state.update { it.copy(sheetState = !it.sheetState) }
    }

    companion object {
        private const val SORT_BY = "deadlineSoon"
    }
}
