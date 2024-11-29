package com.terning.data.mypage.di

import com.terning.data.mypage.service.MyPageService

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideMyPageService(@JWT retrofit: Retrofit): MyPageService =
        retrofit.create(MyPageService::class.java)
}