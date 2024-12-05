package com.terning.data.tokenreissue.di

import com.terning.data.tokenreissue.repositoryimpl.TokenReissueRepositoryImpl
import com.terning.domain.tokenreissue.repository.TokenReissueRepository
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
    abstract fun bindTokenReissueRepository(tokenReissueRepositoryImpl: TokenReissueRepositoryImpl): TokenReissueRepository
}