package com.haroldcalayan.tamingtemper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.haroldcalayan.tamingtemper.common.Resource
import com.haroldcalayan.tamingtemper.data.source.remote.model.TamingActivityResponse
import com.haroldcalayan.tamingtemper.domain.TamingActivityUseCase
import com.haroldcalayan.tamingtemper.presenter.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var tamingActivityUseCaseMock: TamingActivityUseCase

    lateinit var viewModel: HomeViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `test loadTamingActivity success`() = runBlocking {
        // Given
        val expectedResult = Resource.Success(TamingActivityResponse(/* provide necessary parameters */))
        `when`(tamingActivityUseCaseMock()).thenReturn(flowOf(expectedResult))
        viewModel = HomeViewModel(tamingActivityUseCaseMock)
        // Then
        assertEquals(expectedResult, viewModel.tamingActivity.value)
    }

    @Test
    fun `test loadTamingActivity error`() = runBlocking {
        // Given
        val expectedResult: Resource<TamingActivityResponse> = Resource.Error("Error Message")
        `when`(tamingActivityUseCaseMock()).thenReturn(flowOf(expectedResult))

        viewModel = HomeViewModel(tamingActivityUseCaseMock)
        // Then
        assertEquals(expectedResult, viewModel.tamingActivity.value)
    }

    @Test
    fun `test loadTamingActivity loading`() = runBlocking {
        // Given
        val expectedResult: Resource<TamingActivityResponse> = Resource.Loading()
        `when`(tamingActivityUseCaseMock()).thenReturn(flowOf(expectedResult))

        viewModel = HomeViewModel(tamingActivityUseCaseMock)
        // Then
        assertEquals(expectedResult, viewModel.tamingActivity.value)
    }
}
