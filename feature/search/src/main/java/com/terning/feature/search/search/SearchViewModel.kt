package com.terning.feature.search.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.designsystem.state.UiState
import com.terning.feature.search.R
import com.terning.feature.search.search.model.SearchBannerListState
import com.terning.feature.search.search.model.SearchScrapsListState
import com.terning.feature.search.search.model.SearchViewsListState
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
    private val searchRepository: com.terning.domain.search.repository.SearchRepository,
) : ViewModel() {
    private val _viewState: MutableStateFlow<SearchViewsListState> =
        MutableStateFlow(SearchViewsListState())
    val viewState: StateFlow<SearchViewsListState> = _viewState.asStateFlow()

    private val _scrapState: MutableStateFlow<SearchScrapsListState> =
        MutableStateFlow(SearchScrapsListState())
    val scrapState: StateFlow<SearchScrapsListState> = _scrapState.asStateFlow()

    private val _bannerState: MutableStateFlow<SearchBannerListState> =
        MutableStateFlow(SearchBannerListState())
    val bannerState: StateFlow<SearchBannerListState> = _bannerState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SearchSideEffect> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    private val _bannerList: MutableStateFlow<List<com.terning.domain.search.entity.SearchBanner>> =
        MutableStateFlow(emptyList())

    init {
        getSearchViews()
        getSearchScraps()
        getSearchBanners()
    }

    fun getSearchViews() {
        viewModelScope.launch {
            searchRepository.getSearchViewsList()
                .onSuccess { searchViewsList ->
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
            searchRepository.getSearchScrapsList()
                .onSuccess { searchScrapsList ->
                    _scrapState.value = _scrapState.value.copy(
                        searchScrapsList = UiState.Success(searchScrapsList)
                    )
                }.onFailure {
                    _sideEffect.emit(SearchSideEffect.Toast(R.string.server_failure))
                }
        }
    }

    fun getSearchBanners() {
        viewModelScope.launch {
            searchRepository.getSearchBannersList()
                .onSuccess { searchBannersList ->
                    _bannerState.value = _bannerState.value.copy(
                        searchBannersList = UiState.Success(searchBannersList)
                    )
                    _bannerList.value = searchBannersList
                }.onFailure {
                    _sideEffect.emit(SearchSideEffect.Toast(R.string.server_failure))
                }
        }
    }
}
