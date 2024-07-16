package com.terning.point.di

import com.terning.data.datasource.AuthDataSource
import com.terning.data.datasource.InternDataSource
import com.terning.data.datasource.ScrapDataSource
import com.terning.data.datasource.SearchDataSource
import com.terning.data.datasourceimpl.AuthDataSourceImpl
import com.terning.data.datasourceimpl.InternDataSourceImpl
import com.terning.data.datasourceimpl.ScrapDataSourceImpl
import com.terning.data.datasourceimpl.SearchDataSourceImpl
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
    abstract fun bindAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Binds
    @Singleton
    abstract fun bindSearchViewsDataSource(searchViewsDataSourceImpl: SearchDataSourceImpl):
            SearchDataSource

    @Binds
    @Singleton
    abstract fun bindInternInfoDataSource(internalInfoDataSourceImpl: InternDataSourceImpl):
            InternDataSource

    @Binds
    @Singleton
    abstract fun bindScrapDataSource(scrapDataSourceImpl: ScrapDataSourceImpl):
            ScrapDataSource
}