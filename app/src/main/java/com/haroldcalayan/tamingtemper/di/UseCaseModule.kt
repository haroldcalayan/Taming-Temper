package com.haroldcalayan.tamingtemper.di

import com.haroldcalayan.tamingtemper.data.repository.TamingStorageRepository
import com.haroldcalayan.tamingtemper.data.repository.TamingTemperRepository
import com.haroldcalayan.tamingtemper.domain.TamingActivityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideTamingActivityUseCase(
        tamingTemperRepository: TamingTemperRepository,
        tamingStorage: TamingStorageRepository
    ): TamingActivityUseCase {
        return TamingActivityUseCase(tamingTemperRepository, tamingStorage)
    }

}