package com.terning.data.filtering.di

import com.terning.data.filtering.repositoryimpl.FilteringRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindFilteringRepository(filteringRepositoryImpl: FilteringRepositoryImpl): FilteringRepository

}