package com.terning.point.di

import com.terning.data.repositoryimpl.MockRepositoryImpl
import com.terning.data.repositoryimpl.CalendarRepositoryImpl
import com.terning.domain.repository.MockRepository
import com.terning.domain.repository.CalendarRepository
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
    abstract fun bindMockRepository(mockRepositoryImpl: MockRepositoryImpl): MockRepository
}