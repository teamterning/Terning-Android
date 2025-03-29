package com.terning.feature.main.navigator.di

import com.terning.feature.main.navigator.NavigatorProviderIntent
import com.terning.navigator.NavigatorProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigatorModule {
    @Binds
    @Singleton
    fun bindNavigatorIntent(navigatorProviderIntent: NavigatorProviderIntent): NavigatorProvider
}
