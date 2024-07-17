package com.terning.point.di

import com.terning.data.datasource.AuthDataSource
import com.terning.data.datasource.InternDataSource
import com.terning.data.datasource.ScrapDataSource
import com.terning.data.datasource.SearchDataSource
import com.terning.data.datasource.TokenReissueDataSource
import com.terning.data.datasourceimpl.AuthDataSourceImpl
import com.terning.data.datasourceimpl.InternDataSourceImpl
import com.terning.data.datasourceimpl.ScrapDataSourceImpl
import com.terning.data.datasourceimpl.SearchDataSourceImpl
import com.terning.data.datasource.CalendarDataSource
import com.terning.data.datasourceimpl.CalendarDataSourceImpl
import com.terning.data.datasourceimpl.TokenReissueDataSourceImpl
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
    abstract fun bindCalendarDataSource(calendarDataSourceImpl: CalendarDataSourceImpl): CalendarDataSource

    @Binds
    @Singleton
    abstract fun bindAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Binds
    @Singleton
    abstract fun bindSearchViewsDataSource(searchDataSourceImpl: SearchDataSourceImpl):
            SearchDataSource

    @Binds
    @Singleton
    abstract fun bindInternInfoDataSource(internalInfoDataSourceImpl: InternDataSourceImpl):
            InternDataSource

    @Binds
    @Singleton
    abstract fun bindScrapDataSource(scrapDataSourceImpl: ScrapDataSourceImpl):
            ScrapDataSource

    @Binds
    @Singleton
    abstract fun bindTokenReissueDataSource(tokenReissueDataSourceImpl: TokenReissueDataSourceImpl): TokenReissueDataSource
}