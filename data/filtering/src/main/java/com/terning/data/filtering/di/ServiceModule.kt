package com.terning.data.filtering.di

import com.terning.core.network.qualifier.JWT
import com.terning.data.filtering.service.FilteringService
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
    fun provideFilteringService(@JWT retrofit: Retrofit): FilteringService =
        retrofit.create(FilteringService::class.java)
}
