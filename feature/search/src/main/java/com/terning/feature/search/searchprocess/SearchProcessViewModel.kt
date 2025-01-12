package com.terning.feature.search.searchprocess

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.terning.core.designsystem.type.SortBy
import com.terning.domain.search.entity.SearchResult
import com.terning.domain.search.repository.SearchRepository
import com.terning.feature.search.searchprocess.models.SearchProcessState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
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

    private val scrapStateFlow: MutableStateFlow<Map<Long, Boolean>> =
        MutableStateFlow(emptyMap())

    private val _internSearchResultFlow: MutableStateFlow<Flow<PagingData<SearchResult>>> =
        MutableStateFlow(flow { })

    @OptIn(ExperimentalCoroutinesApi::class)
    val internSearchResultFlow: Flow<PagingData<SearchResult>> = combine(
        _internSearchResultFlow.flatMapLatest {
            it.cachedIn(viewModelScope)
        }, scrapStateFlow
    ) { paging, scrapState ->
        paging.map { intern ->
            val isScrapped = scrapState[intern.internshipAnnouncementId] ?: intern.isScrapped
            intern.copy(
                isScrapped = isScrapped
            )
        }
    }

    fun getSearchListFlow() {
        refreshScrapStateFlow()
        refreshSearchListFlow()
    }

    private fun refreshScrapStateFlow() {
        scrapStateFlow.value = emptyMap()
    }

    private fun refreshSearchListFlow() {
        _internSearchResultFlow.value = searchRepository.getSearchList(
            query = _state.value.keyword,
            sortBy = SortBy.entries[_state.value.currentSortBy].type
        ).cachedIn(viewModelScope)
    }

    fun updateSearchResult(
        internshipId: Long,
        title: String,
        dDay: String,
        workingPeriod: String,
        companyImage: String,
        isScrapped: Boolean,
        deadline: String,
        startYearMonth: String,
        color: String?,
        totalCount: Int,
    ) {
        _state.update {
            it.copy(
                searchResult = SearchResult(
                    internshipAnnouncementId = internshipId,
                    title = title,
                    dDay = dDay,
                    workingPeriod = workingPeriod,
                    companyImage = companyImage,
                    isScrapped = isScrapped,
                    deadline = deadline,
                    startYearMonth = startYearMonth,
                    color = color ?: "",
                    totalCount = totalCount
                )
            )
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

    fun updateExistSearchResults() {
        _state.update { it.copy(existSearchResults = false) }
    }

    fun updateScrapDialogVisible(visible: Boolean) {
        _state.update { it.copy(isScrapDialogVisible = visible) }
    }

    fun updateSheetVisible(visible: Boolean) {
        _state.update { it.copy(sheetState = visible) }
    }

    fun updateSortBy(newSortBy: Int) {
        _state.update {
            it.copy(currentSortBy = newSortBy)
        }
        refreshSearchListFlow()
    }

    fun updateSearchResultScrapStatus(internshipId: Long, isScrapped: Boolean) {
        scrapStateFlow.update { currentMap ->
            currentMap + (internshipId to isScrapped)
        }
        _state.update {
            it.copy(
                searchResult = it.searchResult.copy(
                    isScrapped = isScrapped
                )
            )
        }
    }

    fun navigateIntern(internshipAnnouncementId: Long) {
        viewModelScope.launch {
            _sideEffect.emit(SearchProcessSideEffect.NavigateIntern(internshipAnnouncementId))
        }
    }
}
