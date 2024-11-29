package com.terning.data.calendar.di

import com.terning.data.calendar.service.CalendarService

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideCalendarService(@JWT retrofit: Retrofit): CalendarService =
        retrofit.create(CalendarService::class.java)
}