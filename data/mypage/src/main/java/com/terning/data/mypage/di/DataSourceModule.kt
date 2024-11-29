package com.terning.data.mypage.di

import com.terning.data.mypage.datasource.MyPageDataSource
import com.terning.data.mypage.datasourceimpl.MyPageDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindMyPageDataSource(myPageDataSourceImpl: MyPageDataSourceImpl): MyPageDataSource
}