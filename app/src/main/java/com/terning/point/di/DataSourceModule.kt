package com.terning.point.di

import com.terning.data.datasource.MockDataSource
import com.terning.data.datasource.ScrapDataSource
import com.terning.data.datasourceimpl.MockDataSourceImpl
import com.terning.data.datasourceimpl.ScrapDataSourceImpl
import com.terning.data.repositoryimpl.ScrapRepositoryImpl
import com.terning.domain.repository.ScrapRepository
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
    abstract fun bindScrapRepository(scrapDataSourceImpl: ScrapDataSourceImpl): ScrapDataSource

    @Binds
    @Singleton
    abstract fun bindMockDataSource(mockDataSourceImpl: MockDataSourceImpl): MockDataSource
}