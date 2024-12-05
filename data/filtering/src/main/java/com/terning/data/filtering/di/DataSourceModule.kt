package com.terning.data.filtering.di

import com.terning.data.filtering.datasource.FilteringDataSource
import com.terning.data.filtering.datasourceimpl.FilteringDataSourceImpl
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
    abstract fun bindFilteringDataSource(filteringDataSourceImpl: FilteringDataSourceImpl): FilteringDataSource
}