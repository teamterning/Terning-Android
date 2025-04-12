package com.terning.data.update.di

import com.terning.data.update.repositoryimpl.UpdateRepositoryImpl
import com.terning.domain.update.repository.UpdateRepository
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
    abstract fun bindsUpdateRepository(updateRepositoryImpl: UpdateRepositoryImpl): UpdateRepository
}