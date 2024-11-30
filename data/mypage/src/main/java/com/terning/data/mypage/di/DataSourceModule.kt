package com.terning.data.mypage.di

import com.terning.data.mypage.datasource.MyPageDataSource
import com.terning.data.mypage.datasourceimpl.MyPageDataSourceImpl
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
    abstract fun bindMyPageDataSource(myPageDataSourceImpl: MyPageDataSourceImpl): MyPageDataSource
}