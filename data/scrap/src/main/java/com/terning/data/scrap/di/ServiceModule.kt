package com.terning.data.scrap.di

import com.terning.core.network.qualifier.JWT
import com.terning.data.scrap.service.ScrapService
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
    fun provideScrapService(@JWT retrofit: Retrofit): ScrapService =
        retrofit.create(ScrapService::class.java)
}