package com.terning.navigator

import android.content.Intent
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

interface NavigatorProvider {
    fun getMainActivityIntent(): Intent
}

@InstallIn(SingletonComponent::class)
@EntryPoint
interface NavigatorEntryPoint {
    fun navigatorProvider(): NavigatorProvider
}