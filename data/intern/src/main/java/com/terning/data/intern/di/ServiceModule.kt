package com.terning.data.intern.di

import com.terning.core.network.qualifier.JWT
import com.terning.data.intern.service.InternService
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
    fun providerInternService(@JWT retrofit: Retrofit): InternService =
        retrofit.create(InternService::class.java)
}