package com.terning.data.scrap.di

import com.terning.data.scrap.datasource.ScrapDataSource
import com.terning.data.scrap.datasourceimpl.ScrapDataSourceImpl
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
    abstract fun bindScrapDataSource(scrapDataSourceImpl: ScrapDataSourceImpl): ScrapDataSource

}