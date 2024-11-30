package com.terning.data.auth.di

import com.terning.data.auth.datasource.AuthDataSource
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
    abstract fun bindAuthDataSource(authDataSourceImpl: com.terning.data.auth.datasourceimpl.AuthDataSourceImpl): AuthDataSource
}