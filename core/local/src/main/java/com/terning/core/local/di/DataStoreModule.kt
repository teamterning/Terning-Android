package com.terning.core.local.di

import android.content.Context
import android.content.SharedPreferences
import com.terning.core.local.TerningDataStore
import com.terning.core.local.TerningDataStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideTerningDataStore(dataStoreImpl: TerningDataStoreImpl): TerningDataStore =
        dataStoreImpl
}