package com.haroldcalayan.tamingtemper.di

import android.content.Context
import com.haroldcalayan.tamingtemper.data.repository.TamingStorageRepository
import com.haroldcalayan.tamingtemper.data.repository.TamingTemperRepository
import com.haroldcalayan.tamingtemper.data.repository.TamingTemperRepositoryImpl
import com.haroldcalayan.tamingtemper.data.source.remote.api.TamingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCurrencyRepository(tamingApi: TamingApi, tamingStorage: TamingStorageRepository): TamingTemperRepository {
        return TamingTemperRepositoryImpl(tamingApi, tamingStorage)
    }

    @Provides
    @Singleton
    fun provideTamingApi(@ApplicationContext context: Context): TamingApi  {
        return TamingApi(context)
    }
}