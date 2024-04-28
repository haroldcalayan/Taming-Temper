package com.haroldcalayan.tamingtemper.domain

import com.haroldcalayan.tamingtemper.common.Resource
import com.haroldcalayan.tamingtemper.data.repository.TamingTemperRepository
import com.haroldcalayan.tamingtemper.data.source.remote.model.TamingActivityResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class TamingActivityUseCase @Inject constructor(
    private val repository: TamingTemperRepository
) {
    val tamingActivity = repository.tamingActivity

    suspend fun load() {
        repository.loadLatestTamingActivity()
    }
}