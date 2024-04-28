package com.haroldcalayan.tamingtemper.presenter.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.haroldcalayan.tamingtemper.common.Resource
import com.haroldcalayan.tamingtemper.presenter.ui.WeekTabLayout
import com.haroldcalayan.tamingtemper.presenter.ui.Appbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state = viewModel.tamingActivity.collectAsState()

            Scaffold(
                topBar = {
                    Appbar()
                }
            ) { paddingValues ->
                Surface(modifier = Modifier.padding(paddingValues)) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        when (state.value) {
                            is Resource.Success -> {
                                WeekTabLayout(state = state.value.data)
                            }
                            else -> {}
                        }

                    }
                }
            }
        }
    }
}