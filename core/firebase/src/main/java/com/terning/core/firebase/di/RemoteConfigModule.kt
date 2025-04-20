package com.terning.core.firebase.di

import com.terning.core.firebase.remoteconfig.TerningRemoteConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteConfigModule {

    @Singleton
    @Provides
    fun provideFirebaseRemoteConfig(): TerningRemoteConfig = TerningRemoteConfig()
}
