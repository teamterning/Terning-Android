package com.terning.point.di

import com.terning.data.repositoryimpl.AuthRepositoryImpl
import com.terning.data.repositoryimpl.CalendarRepositoryImpl
import com.terning.data.repositoryimpl.FilteringRepositoryImpl
import com.terning.data.repositoryimpl.InternRepositoryImpl
import com.terning.data.repositoryimpl.HomeRepositoryImpl
import com.terning.data.repositoryimpl.SearchRepositoryImpl
import com.terning.data.repositoryimpl.TokenReissueRepositoryImpl
import com.terning.data.repositoryimpl.TokenRepositoryImpl
import com.terning.domain.repository.AuthRepository
import com.terning.domain.repository.CalendarRepository
import com.terning.domain.repository.FilteringRepository
import com.terning.domain.repository.InternRepository
import com.terning.domain.repository.HomeRepository
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
    abstract fun calendarRepository(calendarRepositoryImpl: CalendarRepositoryImpl): CalendarRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindTokenRepository(tokenRepositoryImpl: TokenRepositoryImpl): TokenRepository

    @Binds
    @Singleton
    abstract fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository

    @Binds
    @Singleton
    abstract fun bindInternRepository(internRepositoryImpl: InternRepositoryImpl): InternRepository

    @Binds
    @Singleton
    abstract fun bindTokenReissueRepository(tokenReissueRepositoryImpl: TokenReissueRepositoryImpl): TokenReissueRepository

    @Binds
    @Singleton
    abstract fun bindFilteringRepository(filteringRepositoryImpl: FilteringRepositoryImpl): FilteringRepository

    @Binds
    @Singleton
    abstract fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository
}