package com.terning.data.home.di

import com.terning.core.network.qualifier.JWT
import com.terning.data.home.service.HomeService
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
    fun provideHomeService(@JWT retrofit: Retrofit): HomeService =
        retrofit.create(HomeService::class.java)
}