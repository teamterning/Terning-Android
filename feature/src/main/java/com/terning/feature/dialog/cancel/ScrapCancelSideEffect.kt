package com.terning.feature.dialog.cancel

sealed class ScrapCancelSideEffect{
    data class ShowToast(val message: Int): ScrapCancelSideEffect()
    data object DismissDialog : ScrapCancelSideEffect()
}