package com.terning.data.intern.di

import com.terning.data.intern.datasource.InternDataSource
import com.terning.data.intern.datasourceimpl.InternDataSourceImpl
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
    abstract fun bindInternInfoSource(internalInfoDataSourceImpl: InternDataSourceImpl): InternDataSource
}