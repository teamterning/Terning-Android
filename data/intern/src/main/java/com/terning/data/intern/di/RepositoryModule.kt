package com.terning.data.intern.di

import com.terning.data.intern.repositoryimpl.InternRepositoryImpl
import com.terning.domain.intern.repository.InternRepository
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
    abstract fun bindInternRepository(internRepositoryImpl: InternRepositoryImpl): InternRepository

}