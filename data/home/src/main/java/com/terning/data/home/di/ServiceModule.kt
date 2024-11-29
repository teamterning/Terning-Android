package com.terning.data.home.di

import com.terning.data.home.service.HomeService

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideHomeService(@JWT retrofit: Retrofit): HomeService =
        retrofit.create(HomeService::class.java)
}