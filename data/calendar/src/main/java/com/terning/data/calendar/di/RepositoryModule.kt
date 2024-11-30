package com.terning.data.calendar.di

import com.terning.data.calendar.repositoryimpl.CalendarRepositoryImpl
import com.terning.domain.calendar.repository.CalendarRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun calendarRepository(calendarRepositoryImpl: CalendarRepositoryImpl): CalendarRepository
}