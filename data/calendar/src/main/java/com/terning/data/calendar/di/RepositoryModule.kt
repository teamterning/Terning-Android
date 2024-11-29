package com.terning.data.calendar.di

import com.terning.data.calendar.repositoryimpl.CalendarRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun calendarRepository(calendarRepositoryImpl: CalendarRepositoryImpl): CalendarRepository

}