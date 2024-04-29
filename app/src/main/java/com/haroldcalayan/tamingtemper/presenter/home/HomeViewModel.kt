package com.haroldcalayan.tamingtemper.presenter.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haroldcalayan.tamingtemper.common.Resource
import com.haroldcalayan.tamingtemper.data.source.remote.model.TamingActivityResponse
import com.haroldcalayan.tamingtemper.domain.TamingActivityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tamingActivityUseCase: TamingActivityUseCase
) : ViewModel() {

    private val _tamingActivity =
        MutableStateFlow<Resource<TamingActivityResponse>>(Resource.Loading())
    val tamingActivity = _tamingActivity.asStateFlow()

    init {
        loadTamingActivity()
    }

    private fun loadTamingActivity() {
        tamingActivityUseCase().onEach { result ->
            _tamingActivity.value = result
        }.launchIn(viewModelScope)
    }

}