package com.haroldcalayan.tamingtemper.data.repository

import com.haroldcalayan.tamingtemper.common.DataStorageHelper
import com.haroldcalayan.tamingtemper.common.constant.PreferenceKey
import com.haroldcalayan.tamingtemper.data.source.remote.api.TamingApi
import com.haroldcalayan.tamingtemper.data.source.remote.model.TamingActivityResponse

interface TamingTemperRepository {
    suspend fun getLatestTamingActivity(): TamingActivityResponse

}

class TamingTemperRepositoryImpl(
    private val api: TamingApi,
    private val dataStorageHelper: DataStorageHelper
) : TamingTemperRepository {

    override suspend fun getLatestTamingActivity(): TamingActivityResponse {
        //throw Exception() //Uncomment it if you want to make the api failed
        return api.getTamingApi()
            .also { dataStorageHelper.saveValue(PreferenceKey.TAMING_ACTIVITY, it) }
    }

}