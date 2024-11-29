package com.terning.data.search.di

import com.terning.data.search.datasource.SearchDataSource
import com.terning.data.search.datasourceimpl.SearchDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindSearchDataSource(searchDataSourceImpl: SearchDataSourceImpl):
            SearchDataSource
}