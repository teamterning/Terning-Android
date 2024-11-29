package com.terning.data.intern.di

import com.terning.data.intern.repositoryimpl.InternRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindInternRepository(internRepositoryImpl: InternRepositoryImpl): InternRepository

}