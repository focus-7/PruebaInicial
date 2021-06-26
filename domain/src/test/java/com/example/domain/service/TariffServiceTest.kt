package com.example.domain.service

import com.example.domain.entity.Motorcycle
import com.example.domain.repository.TariffRepository
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class TariffServiceTest {
    @Mock
    lateinit var tariffRepository: TariffRepository

    @get:Rule
     val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @InjectMocks
    lateinit var tariffService: TariffService

    @Test
    fun testReadyForFlight() {
        val entryVehicle = 1624352400000 //June 22, 2021, 9:00 a.m
        val vehicle = Motorcycle("TYU78E", 150)

    //    val tariffService = TariffService(tariffRepository)
     //   val planeMock = PowerMockito.mock(TariffService::class.java)

       // Mockito.`when`(planeMock.enterVehicle(entryVehicle, vehicle)).thenReturn(true)

        assertTrue(tariffService.enterVehicle(entryVehicle, vehicle))
     //   assertTrue(planeMock.enterVehicle(entryVehicle, vehicle))
    }

}