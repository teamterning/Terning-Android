package com.terning.point.di

import com.terning.data.datasource.AuthDataSource
import com.terning.data.datasource.SearchDataSource
import com.terning.data.datasourceimpl.AuthDataSourceImpl
import com.terning.data.datasourceimpl.SearchDataSourceImpl
import com.terning.data.datasource.CalendarDataSource
import com.terning.data.datasourceimpl.CalendarDataSourceImpl
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
    abstract fun bindCalendarRepository(calendarDataSourceImpl: CalendarDataSourceImpl): CalendarDataSource

    @Binds
    @Singleton
    abstract fun bindAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Binds
    @Singleton
    abstract fun bindSearchViewsDataSource(searchViewsDataSourceImpl: SearchDataSourceImpl):
            SearchDataSource
}