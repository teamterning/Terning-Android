package com.terning.data.filtering.di

import com.terning.data.filtering.service.FilteringService

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideFilteringService(@JWT retrofit: Retrofit): FilteringService =
        retrofit.create(FilteringService::class.java)
}
