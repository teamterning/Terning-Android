package com.terning.data.tokenreissue.di

import com.terning.core.network.qualifier.REISSUE
import com.terning.data.tokenreissue.service.TokenReissueService
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
    fun provideTokenReissueService(@REISSUE retrofit: Retrofit): TokenReissueService =
        retrofit.create(TokenReissueService::class.java)
}