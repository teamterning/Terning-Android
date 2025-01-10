package com.terning.feature.search.searchprocess

import androidx.annotation.StringRes

sealed class SearchProcessSideEffect {
    data class ShowToast(@StringRes val message: Int) : SearchProcessSideEffect()
    data class ScrapUpdate(val keyword: String) : SearchProcessSideEffect()
    data class NavigateIntern(val internshipAnnouncementId: Long) : SearchProcessSideEffect()
}