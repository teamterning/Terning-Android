package com.terning.point.di

import com.terning.data.service.AuthService
import com.terning.data.service.CalendarService
import com.terning.data.service.SearchService
import com.terning.point.di.qualifier.JWT
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
    fun provideCalendarService(@JWT retrofit: Retrofit): CalendarService =
        retrofit.create(CalendarService::class.java)

}