package com.terning.data.home.di

import com.terning.data.home.datasource.HomeDataSource
import com.terning.data.home.datasourceimpl.HomeDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindHomeDataSource(homeDataSourceImpl: HomeDataSourceImpl): HomeDataSource

}