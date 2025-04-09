package com.terning.navigator

import android.content.Intent

interface NavigatorProvider {
    fun getMainActivityIntent(
        deeplink: String?,
    ): Intent

}