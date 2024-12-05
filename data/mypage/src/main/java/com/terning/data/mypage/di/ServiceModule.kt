package com.terning.data.mypage.di

import com.terning.core.network.qualifier.JWT
import com.terning.data.mypage.service.MyPageService
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
    fun provideMyPageService(@JWT retrofit: Retrofit): MyPageService =
        retrofit.create(MyPageService::class.java)
}