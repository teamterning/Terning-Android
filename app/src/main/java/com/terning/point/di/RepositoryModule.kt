package com.terning.point.di

import com.terning.data.repositoryimpl.MockRepositoryImpl
import com.terning.domain.repository.MockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMockRepository(mockRepositoryImpl: MockRepositoryImpl): MockRepository =
        mockRepositoryImpl

}