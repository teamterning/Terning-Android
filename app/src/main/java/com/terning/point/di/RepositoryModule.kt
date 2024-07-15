package com.terning.point.di

import com.terning.data.repositoryimpl.HomeRepositoryImpl
import com.terning.data.repositoryimpl.MockRepositoryImpl
import com.terning.domain.repository.HomeRepository
import com.terning.domain.repository.MockRepository
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
    abstract fun bindMockRepository(mockRepositoryImpl: MockRepositoryImpl): MockRepository

    @Binds
    @Singleton
    abstract fun bindInternRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository
}