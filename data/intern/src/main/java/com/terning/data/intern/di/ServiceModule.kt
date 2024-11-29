package com.terning.data.intern.di

import com.terning.data.intern.service.InternService

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providerInternService(@JWT retrofit: Retrofit): InternService =
        retrofit.create(InternService::class.java)
}