package com.terning.point.di

import com.terning.data.service.AuthService
import com.terning.data.service.MockService
import com.terning.point.di.qualifier.JWT
import com.terning.point.di.qualifier.OPEN
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
    fun provideMockService(@OPEN retrofit: Retrofit): MockService =
        retrofit.create(MockService::class.java)

    @Provides
    @Singleton
    fun provideAuthService(@JWT retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)
}