package com.terning.data.search.di

import com.terning.core.network.qualifier.JWT
import com.terning.data.search.service.SearchService
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
    fun provideSearchService(@JWT retrofit: Retrofit): SearchService =
        retrofit.create(SearchService::class.java)
}