package com.terning.core.firebase.di

import com.terning.core.firebase.fcmtoken.FcmTokenProvider
import com.terning.core.firebase.fcmtoken.FcmTokenProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FcmFirebaseModule {
    @Provides
    @Singleton
    fun provideFcmFirebaseProviderImpl(): FcmTokenProvider = FcmTokenProviderImpl()
}
