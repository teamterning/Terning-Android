package com.terning.point.di

import com.terning.data.datasource.AuthDataSource
import com.terning.data.datasource.MockDataSource
import com.terning.data.datasourceimpl.AuthDataSourceImpl
import com.terning.data.datasourceimpl.MockDataSourceImpl
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
    abstract fun bindMockDataSource(mockDataSourceImpl: MockDataSourceImpl): MockDataSource

    @Binds
    @Singleton
    abstract fun bindAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource
}