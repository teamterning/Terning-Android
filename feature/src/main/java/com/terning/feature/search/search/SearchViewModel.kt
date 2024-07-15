package com.terning.feature.search.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.state.UiState
import com.terning.domain.entity.response.InternAnnouncementResponseModel
import com.terning.domain.repository.SearchScrapsRepository
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
    private val searchScrapsRepository: SearchScrapsRepository,
) : ViewModel() {
    private val _viewState: MutableStateFlow<SearchState> = MutableStateFlow(SearchState())
    val viewState: StateFlow<SearchState> = _viewState.asStateFlow()

    private val _scrapState: MutableStateFlow<SearchState> = MutableStateFlow(SearchState())
    val scrapState: StateFlow<SearchState> = _scrapState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SearchSideEffect> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        getSearchViews()
    }

    fun getSearchViews() {
        viewModelScope.launch {
            searchViewsRepository.getSearchViewsList().onSuccess { response ->
                val searchViewsList = response.map { entity ->
                    InternAnnouncementResponseModel(
                        title = entity.title,
                        companyImage = entity.companyImage,
                        announcementId = entity.announcementId
                    )
                }
                _viewState.value = _viewState.value.copy(
                    searchViewsList = UiState.Success(searchViewsList)
                )
                _sideEffect.emit(SearchSideEffect.Toast(R.string.server_success))
            }.onFailure {
                _sideEffect.emit(SearchSideEffect.Toast(R.string.server_failure))
            }

            searchScrapsRepository.getSearchScrapsList().onSuccess { response ->
                val searchScrapsList = response.map { entity ->
                    InternAnnouncementResponseModel(
                        title = entity.title,
                        companyImage = entity.companyImage,
                        announcementId = entity.announcementId
                    )
                }
                _scrapState.value = _scrapState.value.copy(
                    searchScrapsList = UiState.Success(searchScrapsList)
                )
                _sideEffect.emit(SearchSideEffect.Toast(R.string.server_success))
            }.onFailure {
                _sideEffect.emit(SearchSideEffect.Toast(R.string.server_failure))
            }
        }
    }
}