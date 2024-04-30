package com.haroldcalayan.tamingtemper

import com.haroldcalayan.tamingtemper.common.DataStorageHelper
import com.haroldcalayan.tamingtemper.data.repository.TamingTemperRepositoryImpl
import com.haroldcalayan.tamingtemper.data.source.remote.api.TamingApi
import com.haroldcalayan.tamingtemper.data.source.remote.model.TamingActivityResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class TamingTemperRepositoryImplTest {

    @Mock
    private lateinit var mockApi: TamingApi

    @Mock
    private lateinit var dataStorageHelper: DataStorageHelper

    private lateinit var repository: TamingTemperRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = TamingTemperRepositoryImpl(mockApi, dataStorageHelper)
    }

    @Test
    fun `getLatestTamingActivity success`() = runBlocking {
        // Given
        val expectedResponse = TamingActivityResponse(/* provide expected response data */)
        `when`(mockApi.getTamingApi()).thenReturn(expectedResponse)

        // When
        val result = repository.getLatestTamingActivity()

        // Then
        assertEquals(expectedResponse, result)
    }

    @Test(expected = Exception::class)
    fun `getLatestTamingActivity failure`(): Unit = runBlocking {
        // Given
        `when`(mockApi.getTamingApi()).thenThrow(Exception())

        // When
        val result = repository.getLatestTamingActivity()

        assertEquals(Exception(), result)

        // Then
        // Exception should be thrown
    }
}