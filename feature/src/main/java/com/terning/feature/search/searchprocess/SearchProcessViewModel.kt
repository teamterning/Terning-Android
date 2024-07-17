package com.terning.feature.search.searchprocess

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.state.UiState
import com.terning.domain.repository.SearchRepository
import com.terning.feature.home.home.model.InternData
import com.terning.feature.search.searchprocess.models.SearchProcessState
import com.terning.feature.search.searchprocess.models.SearchResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchProcessViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
) : ViewModel() {
    private val _state: MutableStateFlow<SearchProcessState> =
        MutableStateFlow(SearchProcessState())
    val state: StateFlow<SearchProcessState> get() = _state

    private val _searchListState: MutableStateFlow<SearchResultState> =
        MutableStateFlow(SearchResultState())
    val searchListState: StateFlow<SearchResultState> get() = _searchListState

    private val _internSearchResultState = MutableStateFlow(
        getRecommendData()
    )
    val internSearchResultData get() = _internSearchResultState.asStateFlow()

    fun getSearchList(
        query: String,
        sortBy: String,
        page: Int,
        size: Int,
    ) {
        viewModelScope.launch {
            searchRepository.getSearchList(query, sortBy, page, size)
                .onSuccess {
                    _searchListState.value = _searchListState.value.copy(
                        searchList = UiState.Success(it)
                    )
                }
                .onFailure {
                    _searchListState.value = _searchListState.value.copy(
                        searchList = UiState.Failure(it.message.toString())
                    )
                }
        }
    }

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


    fun updateExistSearchResults(query: String) {
        val exist =
            _internSearchResultState.value.any { it.title.contains(query, ignoreCase = true) }
        _state.value = _state.value.copy(existSearchResults = exist)
    }
}

private fun getRecommendData(): List<InternData> = listOf(
    InternData(
        imgUrl = "https://reqres.in/img/faces/7-image.jpg",
        title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        dDay = 22,
        workingPeriod = 2,
        isScrapped = true,
    ),
    InternData(
        imgUrl = "https://reqres.in/img/faces/7-image.jpg",
        title = "ㅇㄻㅇㅁㄻㄹㅇㅁㅇㄹ",
        dDay = 9,
        workingPeriod = 6,
        isScrapped = false,
    ),
    InternData(
        imgUrl = "https://reqres.in/img/faces/7-image.jpg",
        title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        dDay = 2,
        workingPeriod = 4,
        isScrapped = true,
    ),
    InternData(
        imgUrl = "https://reqres.in/img/faces/7-image.jpg",
        title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        dDay = 2,
        workingPeriod = 4,
        isScrapped = false,
    ),
    InternData(
        imgUrl = "https://reqres.in/img/faces/7-image.jpg",
        title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        dDay = 2,
        workingPeriod = 4,
        isScrapped = true,
    ),
    InternData(
        imgUrl = "https://reqres.in/img/faces/7-image.jpg",
        title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        dDay = 2,
        workingPeriod = 4,
        isScrapped = true,
    ),
    InternData(
        imgUrl = "https://reqres.in/img/faces/7-image.jpg",
        title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        dDay = 2,
        workingPeriod = 4,
        isScrapped = false,
    ),
    InternData(
        imgUrl = "https://reqres.in/img/faces/7-image.jpg",
        title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        dDay = 2,
        workingPeriod = 4,
        isScrapped = true,
    ),
    InternData(
        imgUrl = "https://reqres.in/img/faces/7-image.jpg",
        title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        dDay = 2,
        workingPeriod = 4,
        isScrapped = false,
    ),
    InternData(
        imgUrl = "https://reqres.in/img/faces/7-image.jpg",
        title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        dDay = 2,
        workingPeriod = 4,
        isScrapped = true,
    ),
)