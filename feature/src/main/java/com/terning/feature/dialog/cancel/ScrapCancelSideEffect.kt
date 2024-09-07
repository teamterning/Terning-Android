package com.terning.feature.dialog.cancel

import androidx.annotation.StringRes

sealed class ScrapCancelSideEffect{
    data class ShowToast(@StringRes val message: Int): ScrapCancelSideEffect()
    data object DismissDialog : ScrapCancelSideEffect()
}