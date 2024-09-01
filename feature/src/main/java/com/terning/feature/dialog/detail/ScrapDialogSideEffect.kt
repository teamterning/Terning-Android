package com.terning.feature.dialog.detail

sealed class ScrapDialogSideEffect{
    data class ShowToast(val message: Int): ScrapDialogSideEffect()
    data object DismissDialog : ScrapDialogSideEffect()
    data object ScrappedAnnouncement : ScrapDialogSideEffect()
    data object ChangedColor: ScrapDialogSideEffect()
    data object NavigateToDetail : ScrapDialogSideEffect()
}