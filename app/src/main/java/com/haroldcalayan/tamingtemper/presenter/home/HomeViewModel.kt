package com.haroldcalayan.tamingtemper.presenter.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haroldcalayan.tamingtemper.common.Resource
import com.haroldcalayan.tamingtemper.data.source.remote.model.TamingActivityResponse
import com.haroldcalayan.tamingtemper.domain.TamingActivityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    private val tamingActivityUseCase: TamingActivityUseCase
) : ViewModel() {

    val tamingActivity: StateFlow<Resource<TamingActivityResponse>> = tamingActivityUseCase.tamingActivity

    init {
        loadTamingActivity()
    }

    private fun loadTamingActivity() {
        viewModelScope.launch {
            tamingActivityUseCase.load()
        }
    }

}