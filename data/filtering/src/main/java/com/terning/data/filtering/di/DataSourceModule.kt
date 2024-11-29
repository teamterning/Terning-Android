package com.terning.data.filtering.di

import com.terning.data.filtering.datasource.FilteringDataSource
import com.terning.data.filtering.datasourceimpl.FilteringDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindFilteringDataSource(filteringDataSourceImpl: FilteringDataSourceImpl): FilteringDataSource

}