package com.terning.data.scrap.di

import com.terning.data.scrap.repositoryimpl.ScrapRepositoryImpl
import com.terning.domain.scrap.repository.ScrapRepository
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
    abstract fun bindScrapRepository(scrapRepositoryImpl: ScrapRepositoryImpl): ScrapRepository

}