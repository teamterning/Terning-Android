package com.terning.point.di

import com.terning.data.repositoryimpl.AuthRepositoryImpl
import com.terning.data.repositoryimpl.SearchViewsRepositoryImpl
import com.terning.data.repositoryimpl.TokenReissueRepositoryImpl
import com.terning.data.repositoryimpl.TokenRepositoryImpl
import com.terning.domain.repository.AuthRepository
import com.terning.domain.repository.SearchRepository
import com.terning.domain.repository.TokenReissueRepository
import com.terning.domain.repository.TokenRepository
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
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindTokenRepository(tokenRepositoryImpl: TokenRepositoryImpl): TokenRepository

    @Binds
    @Singleton
    abstract fun bindSearchViewsRepository(searchViewsRepositoryImpl: SearchViewsRepositoryImpl): SearchRepository

    @Binds
    @Singleton
    abstract fun bindTokenReissueRepository(tokenReissueRepositoryImpl: TokenReissueRepositoryImpl): TokenReissueRepository
}