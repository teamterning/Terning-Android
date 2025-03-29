package com.terning.feature.main.navigator

import android.content.Context
import android.content.Intent
import com.terning.feature.main.main.MainActivity
import com.terning.navigator.NavigatorProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NavigatorProviderIntent @Inject constructor(
    @ApplicationContext private val context: Context,
) : NavigatorProvider {
    override fun getMainActivityIntent(): Intent = MainActivity.getIntent(context)

}