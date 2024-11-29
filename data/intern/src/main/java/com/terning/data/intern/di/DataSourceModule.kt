package com.terning.data.intern.di

import com.terning.data.intern.datasource.InternDataSource
import com.terning.data.intern.datasourceimpl.InternDataSourceImpl


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindInternInfoSource(internalInfoDataSourceImpl: InternDataSourceImpl): InternDataSource
}