package com.terning.core.firebase.remoteconfig

import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.terning.core.firebase.remoteconfig.RemoteConfigKey.LATEST_APP_VERSION
import com.terning.core.firebase.remoteconfig.RemoteConfigKey.MAJOR_UPDATE_BODY
import com.terning.core.firebase.remoteconfig.RemoteConfigKey.MAJOR_UPDATE_TITLE
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


