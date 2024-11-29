package com.terning.data.scrap.di

import com.terning.data.scrap.repositoryimpl.ScrapRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindScrapRepository(scrapRepositoryImpl: ScrapRepositoryImpl): ScrapRepository

}