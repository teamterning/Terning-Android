package com.terning.data.auth.di

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindAuthDataSource(authDataSourceImpl: com.terning.data.auth.datasourceimpl.AuthDataSourceImpl):AuthDataSource
}