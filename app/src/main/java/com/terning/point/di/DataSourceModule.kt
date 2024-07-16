package com.terning.point.di

import com.terning.data.datasource.AuthDataSource
import com.terning.data.datasource.SearchScrapsDataSource
import com.terning.data.datasource.SearchViewsDataSource
import com.terning.data.datasourceimpl.AuthDataSourceImpl
import com.terning.data.datasourceimpl.SearchScrapsDataSourceImpl
import com.terning.data.datasourceimpl.SearchViewsDataSourceImpl
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
    abstract fun bindSearchViewsDataSource(searchViewsDataSourceImpl: SearchViewsDataSourceImpl):
            SearchViewsDataSource

    @Binds
    @Singleton
    abstract fun bindSearchScrapsDataSource(searchScrapsDataSourceImpl: SearchScrapsDataSourceImpl):
            SearchScrapsDataSource
}