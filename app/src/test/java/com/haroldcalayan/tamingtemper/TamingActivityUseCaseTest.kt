package com.haroldcalayan.tamingtemper

import com.haroldcalayan.tamingtemper.common.DataStorageHelper
import com.haroldcalayan.tamingtemper.common.Resource
import com.haroldcalayan.tamingtemper.common.constant.PreferenceKey
import com.haroldcalayan.tamingtemper.data.repository.TamingTemperRepository
import com.haroldcalayan.tamingtemper.data.source.remote.model.TamingActivityResponse
import com.haroldcalayan.tamingtemper.domain.TamingActivityUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class TamingActivityUseCaseTest {

    // Mocking the repository and dataStorageHelper
    @Mock
    private lateinit var repository: TamingTemperRepository

    @Mock
    private lateinit var dataStorageHelper: DataStorageHelper

    // The class under test
    private lateinit var useCase: TamingActivityUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = TamingActivityUseCase(repository, dataStorageHelper)
    }

    @Test
    fun `invoke should return loading and success state`() = runBlocking {
        // Given
        val fakeResponse = TamingActivityResponse(/* provide necessary parameters */)
        val fakeLoading: Resource<TamingActivityResponse> = Resource.Loading()
        `when`(repository.getLatestTamingActivity()).thenReturn(fakeResponse)

        // When
        val flow = useCase()

        // Then
        val result = mutableListOf<Resource<TamingActivityResponse>>()
        flow.collect { result.add(it) }
        assertEquals(Resource.Success(fakeResponse).data, result[1].data)
    }

    @Test(expected = Exception::class)
    fun `invoke should return error state when repository throws exception`() = runBlocking {
        val fakeError: Resource<TamingActivityResponse> = Resource.Error("Can't Load Taming Activity")
        // Given
        `when`(repository.getLatestTamingActivity()).thenThrow(Exception("Fake error message"))

        // When
        val flow = useCase()

        // Then
        val result = mutableListOf<Resource<TamingActivityResponse>>()
        flow.collect { result.add(it) }
        assertEquals(fakeError.message, result[0].message)
    }

    @Test(expected = Exception::class)
    fun `invoke should return success state from dataStorageHelper when repository fails`() = runBlockingTest {
        // Given
        val fakeResponse = TamingActivityResponse(/* provide necessary parameters */)
        `when`(repository.getLatestTamingActivity()).thenThrow(Exception("Fake error message"))
        `when`(dataStorageHelper.getValue<TamingActivityResponse>(PreferenceKey.TAMING_ACTIVITY)).thenReturn(fakeResponse)

        // When
        val flow = useCase()

        // Then
        val result = mutableListOf<Resource<TamingActivityResponse>>()
        flow.collect { result.add(it) }
        assertEquals(1, result.size)
        assertEquals(Resource.Success(fakeResponse), result[0])
    }
}
