package com.haroldcalayan.tamingtemper.data.repository

import com.haroldcalayan.tamingtemper.common.Resource
import com.haroldcalayan.tamingtemper.common.appScope
import com.haroldcalayan.tamingtemper.common.constant.PreferenceKey
import com.haroldcalayan.tamingtemper.data.source.remote.api.TamingApi
import com.haroldcalayan.tamingtemper.data.source.remote.model.TamingActivityResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

interface TamingTemperRepository {
    suspend fun getLatestTamingActivity(): TamingActivityResponse

}

class TamingTemperRepositoryImpl(
    private val api: TamingApi,
    private val tamingStorage: TamingStorageRepository
): TamingTemperRepository {

    override suspend fun getLatestTamingActivity(): TamingActivityResponse {
        return api.getTamingApi()
            .also { tamingStorage.saveTamingActivityValue(it) }
    }

}