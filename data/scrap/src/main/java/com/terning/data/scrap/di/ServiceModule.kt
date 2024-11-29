package com.terning.data.scrap.di

import com.terning.data.scrap.service.ScrapService

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideScrapService(@JWT retrofit: Retrofit): ScrapService =
        retrofit.create(ScrapService::class.java)
}