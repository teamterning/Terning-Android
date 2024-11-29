package com.terning.data.calendar.di

import com.terning.data.calendar.datasource.CalendarDataSource
import com.terning.data.calendar.datasourceimpl.CalendarDataSourceImpl


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindCalendarDataSource(calendarDataSourceImpl: CalendarDataSourceImpl): CalendarDataSource

}