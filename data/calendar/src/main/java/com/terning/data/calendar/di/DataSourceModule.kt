package com.terning.data.calendar.di

import com.terning.data.calendar.datasource.CalendarDataSource
import com.terning.data.calendar.datasourceimpl.CalendarDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindCalendarDataSource(calendarDataSourceImpl: CalendarDataSourceImpl): CalendarDataSource
}