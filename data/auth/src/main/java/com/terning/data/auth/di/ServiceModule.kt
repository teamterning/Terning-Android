package com.terning.data.auth.di

import com.terning.core.network.qualifier.JWT
import com.terning.data.auth.service.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideAuthService(@JWT retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)
}