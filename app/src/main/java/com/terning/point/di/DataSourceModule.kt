package com.terning.point.di

import com.terning.data.datasource.AuthDataSource
import com.terning.data.datasource.CalendarDataSource
import com.terning.data.datasource.FilteringDataSource
import com.terning.data.datasource.HomeDataSource
import com.terning.data.datasource.SearchDataSource
import com.terning.data.datasource.TokenReissueDataSource
import com.terning.data.datasourceimpl.AuthDataSourceImpl
import com.terning.data.datasourceimpl.CalendarDataSourceImpl
import com.terning.data.datasourceimpl.FilteringDataSourceImpl
import com.terning.data.datasourceimpl.HomeDataSourceImpl
import com.terning.data.datasourceimpl.SearchDataSourceImpl
import com.terning.data.datasourceimpl.TokenReissueDataSourceImpl
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

    @Binds
    @Singleton
    abstract fun bindAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Binds
    @Singleton
    abstract fun bindSearchViewsDataSource(searchDataSourceImpl: SearchDataSourceImpl):
            SearchDataSource

    @Binds
    @Singleton
    abstract fun bindTokenReissueDataSource(tokenReissueDataSourceImpl: TokenReissueDataSourceImpl): TokenReissueDataSource

    @Binds
    @Singleton
    abstract fun bindFilteringDataSource(filteringDataSourceImpl: FilteringDataSourceImpl): FilteringDataSource

    @Binds
    @Singleton
    abstract fun bindHomeDataSource(homeDataSourceImpl: HomeDataSourceImpl): HomeDataSource
}