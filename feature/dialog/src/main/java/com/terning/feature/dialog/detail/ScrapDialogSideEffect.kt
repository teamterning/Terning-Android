package com.terning.feature.dialog.detail

import androidx.annotation.StringRes

sealed class ScrapDialogSideEffect{
    data class ShowToast(@StringRes val message: Int): ScrapDialogSideEffect()
    data object DismissDialog : ScrapDialogSideEffect()
    data object ScrappedAnnouncement : ScrapDialogSideEffect()
    data object PatchedScrap: ScrapDialogSideEffect()
    data object NavigateToDetail : ScrapDialogSideEffect()
}