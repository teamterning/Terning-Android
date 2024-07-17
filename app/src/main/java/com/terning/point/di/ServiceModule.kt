package com.terning.point.di

import com.terning.data.service.AuthService
import com.terning.data.service.HomeService
import com.terning.data.service.SearchService
import com.terning.data.service.TokenReissueService
import com.terning.point.di.qualifier.JWT
import com.terning.point.di.qualifier.REISSUE
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
    fun provideAuthService(@JWT retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideSearchService(@JWT retrofit: Retrofit): SearchService =
        retrofit.create(SearchService::class.java)

    @Provides
    @Singleton
    fun provideTokenReissueService(@REISSUE retrofit: Retrofit): TokenReissueService =
        retrofit.create(TokenReissueService::class.java)

    @Provides
    @Singleton
    fun provideHomeService(@JWT retrofit: Retrofit): HomeService =
        retrofit.create(HomeService::class.java)
}