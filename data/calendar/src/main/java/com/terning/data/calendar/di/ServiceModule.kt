package com.terning.data.calendar.di

import com.terning.core.network.qualifier.JWT
import com.terning.data.calendar.service.CalendarService
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
    fun provideCalendarService(@JWT retrofit: Retrofit): CalendarService =
        retrofit.create(CalendarService::class.java)
}