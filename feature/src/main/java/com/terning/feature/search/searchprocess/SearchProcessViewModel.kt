package com.terning.feature.search.searchprocess

import androidx.lifecycle.ViewModel
import com.terning.feature.search.searchprocess.models.SearchProcessState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchProcessViewModel @Inject constructor() : ViewModel() {
    private val _state: MutableStateFlow<SearchProcessState> =
        MutableStateFlow(SearchProcessState())

    val state: StateFlow<SearchProcessState> get() = _state

    fun updateText(newText: String) {
        _state.value = _state.value.copy(text = newText)
    }

    fun updateQuery(query: String) {
        _state.value = _state.value.copy(query = query)
    }

    fun updateShowSearchResults(show: Boolean) {
        _state.value = _state.value.copy(showSearchResults = show)
        _state.value = _state.value.copy(existSearchResults = true)
    }
}
