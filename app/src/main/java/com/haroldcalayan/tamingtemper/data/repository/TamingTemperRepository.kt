package com.haroldcalayan.tamingtemper.data.repository

import com.haroldcalayan.tamingtemper.common.Resource
import com.haroldcalayan.tamingtemper.common.appScope
import com.haroldcalayan.tamingtemper.data.source.remote.api.TamingApi
import com.haroldcalayan.tamingtemper.data.source.remote.model.TamingActivityResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

interface TamingTemperRepository {
    val tamingActivity: StateFlow<Resource<TamingActivityResponse>>
    suspend fun loadLatestTamingActivity()
}

class TamingTemperRepositoryImpl(
    private val api: TamingApi,
    private val prefRepo: PreferenceRepository
): TamingTemperRepository {

    override val tamingActivity = MutableStateFlow<Resource<TamingActivityResponse>>(Resource.Loading())

    init {
        appScope.launch {
            prefRepo.tamingActivityFlow.collect {
                it?.let { activity ->
                    tamingActivity.value = Resource.Success(activity)
                }
            }
        }
    }

    override suspend fun loadLatestTamingActivity() {
        try {
            val result = api.getTamingApi()
            tamingActivity.value = Resource.Success(result).also {
                it.data?.let { data ->
                    prefRepo.saveTamingActivityValue(data)
                }
            }
        }
        catch(e: Throwable) {
            Timber.d("tamingActivity -> success")
            tamingActivity.value.data?.let {
                return
            }

            tamingActivity.value = Resource.Error("Can't Load Taming Activity")
            Timber.d("tamingActivity -> error: %s", tamingActivity.value.message)
        }
    }

}