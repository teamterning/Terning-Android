package com.terning.data.filtering.di

import com.terning.data.filtering.repositoryimpl.FilteringRepositoryImpl
import com.terning.domain.filtering.repository.FilteringRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindFilteringRepository(filteringRepositoryImpl: FilteringRepositoryImpl): FilteringRepository
}