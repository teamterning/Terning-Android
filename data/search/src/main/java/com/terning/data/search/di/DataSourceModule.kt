package com.terning.data.search.di

import com.terning.data.search.datasource.SearchDataSource
import com.terning.data.search.datasourceimpl.SearchDataSourceImpl
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
    abstract fun bindSearchDataSource(searchDataSourceImpl: SearchDataSourceImpl):
            SearchDataSource
}