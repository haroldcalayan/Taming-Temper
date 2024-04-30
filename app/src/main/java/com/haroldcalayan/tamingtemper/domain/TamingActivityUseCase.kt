package com.haroldcalayan.tamingtemper.domain

import com.haroldcalayan.tamingtemper.common.DataStorageHelper
import com.haroldcalayan.tamingtemper.common.Resource
import com.haroldcalayan.tamingtemper.common.constant.PreferenceKey
import com.haroldcalayan.tamingtemper.data.repository.TamingTemperRepository
import com.haroldcalayan.tamingtemper.data.source.remote.model.TamingActivityResponse
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TamingActivityUseCase @Inject constructor(
    private val repository: TamingTemperRepository,
    private val dataStorageHelper: DataStorageHelper
) {
    operator fun invoke(): Flow<Resource<TamingActivityResponse>> = flow {
        emit(Resource.Loading())
        val data = repository.getLatestTamingActivity()
        emit(Resource.Success(data))
    }.catch { e ->
        dataStorageHelper.getValue<TamingActivityResponse>(PreferenceKey.TAMING_ACTIVITY)?.let {
            emit(Resource.Success(it))
        } ?: run {
            emit(Resource.Error("Can't Load Taming Activity"))
        }
    }
}