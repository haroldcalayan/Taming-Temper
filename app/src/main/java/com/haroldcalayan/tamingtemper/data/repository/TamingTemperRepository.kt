package com.haroldcalayan.tamingtemper.data.repository

import com.haroldcalayan.tamingtemper.data.source.remote.api.TamingApi
import com.haroldcalayan.tamingtemper.data.source.remote.model.TamingActivityResponse

interface TamingTemperRepository {
    suspend fun getLatestTamingActivity(): TamingActivityResponse

}

class TamingTemperRepositoryImpl(
    private val api: TamingApi,
    private val tamingStorage: TamingStorageRepository
) : TamingTemperRepository {

    override suspend fun getLatestTamingActivity(): TamingActivityResponse {
        return api.getTamingApi()
            .also { tamingStorage.saveTamingActivityValue(it) }
    }

}