package com.terning.feature.search.searchprocess

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.domain.entity.response.SearchResultModel
import com.terning.domain.repository.SearchRepository
import com.terning.feature.R
import com.terning.feature.search.searchprocess.models.SearchProcessState
import com.terning.feature.search.searchprocess.models.SearchResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchProcessViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
) : ViewModel() {
    private val _state: MutableStateFlow<SearchProcessState> =
        MutableStateFlow(SearchProcessState())
    val state: StateFlow<SearchProcessState> = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SearchProcessSideEffect> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    private val _searchListState: MutableStateFlow<SearchResultState> =
        MutableStateFlow(SearchResultState())
    val searchListState: StateFlow<SearchResultState> = _searchListState.asStateFlow()

    private val _internSearchResultData = MutableStateFlow<List<SearchResultModel>>(emptyList())
    val internSearchResultData: StateFlow<List<SearchResultModel>> =
        _internSearchResultData.asStateFlow()

    fun getSearchList(
        keyword: String,
        sortBy: String,
        page: Int,
        size: Int,
    ) {
        viewModelScope.launch {
            searchRepository.getSearchList(keyword, sortBy, page, size)
                .onSuccess {
                    _internSearchResultData.value = it
                }
                .onFailure {
                    _sideEffect.emit(SearchProcessSideEffect.Toast(R.string.server_failure))
                }
        }
    }

    fun updateText(newText: String) {
        _state.value = _state.value.copy(text = newText)
    }

    fun updateQuery(query: String) {
        _state.value = _state.value.copy(keyword = query)
    }

    fun updateShowSearchResults(show: Boolean) {
        _state.value = _state.value.copy(showSearchResults = show)
        _state.value = _state.value.copy(existSearchResults = true)
    }

    fun updateExistSearchResults(query: String) {
        val exist =
            _internSearchResultData.value.any { it.title.contains(query, ignoreCase = true) }
        _state.value = _state.value.copy(existSearchResults = exist)
    }

    companion object {
        private const val SORT_BY = "deadlineSoon"
    }
}
