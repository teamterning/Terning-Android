package com.terning.data.scrap.di

import com.terning.data.scrap.datasource.ScrapDataSource
import com.terning.data.scrap.datasourceimpl.ScrapDataSourceImpl


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindScrapDataSource(scrapDataSourceImpl: ScrapDataSourceImpl): ScrapDataSource

}