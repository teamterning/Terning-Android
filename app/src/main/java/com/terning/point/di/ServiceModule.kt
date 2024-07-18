package com.terning.point.di

import com.terning.data.service.AuthService
import com.terning.data.service.CalendarService
import com.terning.data.service.InternService
import com.terning.data.service.FilteringService
import com.terning.data.service.HomeService
import com.terning.data.service.MyPageService
import com.terning.data.service.ScrapService
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
    fun providerInternService(@JWT retrofit: Retrofit): InternService =
        retrofit.create(InternService::class.java)

    @Provides
    @Singleton
    fun provideCalendarService(@JWT retrofit: Retrofit): CalendarService =
        retrofit.create(CalendarService::class.java)


    @Provides
    @Singleton
    fun provideTokenReissueService(@REISSUE retrofit: Retrofit): TokenReissueService =
        retrofit.create(TokenReissueService::class.java)

    @Provides
    @Singleton
    fun provideFilteringService(@JWT retrofit: Retrofit): FilteringService =
        retrofit.create(FilteringService::class.java)

    @Provides
    @Singleton
    fun provideHomeService(@JWT retrofit: Retrofit): HomeService =
        retrofit.create(HomeService::class.java)

    @Provides
    @Singleton
    fun provideMyPageService(@JWT retrofit: Retrofit): MyPageService =
        retrofit.create(MyPageService::class.java)

    @Provides
    @Singleton
    fun provideScrapService(@JWT retrofit: Retrofit): ScrapService =
        retrofit.create(ScrapService::class.java)
}