package com.example.domain.service

import com.example.domain.aggregate.Tariff
import com.example.domain.entity.Car
import com.example.domain.entity.Motorcycle
import com.example.domain.repository.TariffRepository
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class TariffServiceTest {
    @Mock
    lateinit var tariffService: TariffService

    @Mock
    lateinit var tariffRepository: TariffRepository

    @Test
    fun enterVehicle_WithCorrectParameters_successful() {
        //Arrange
        val entryVehicle = 1624352400000 //June 22, 2021, 9:00 a.m
        val vehicle = Motorcycle("TYU78E", 150)

        //Act
        tariffService.enterVehicle(entryVehicle, vehicle)
    }

    @Test
    fun takeOut_vehicleWithCorrectParameters_successful() {
        //Arrange
        val entryVehicle = 1624266000000 //June 21, 2021, 9:00 a.m
        val vehicle = Car("TYU78E")
        val motorcycle = Tariff(entryVehicle, vehicle)
        motorcycle.vehicleDepartureDate = 1624366800000 //June 22, 2021, 1:00 p.m

        //Act
        tariffService.takeOutVehicle(motorcycle)
    }

    @Test
    fun get_vehiclesByPlate_successful() {
        //Arrange
        val vehicle = Car("TYU78E")

        //Act
        tariffService.getVehiclesByPlate(vehicle.plate)
    }

    @Test
    fun get_allVehicles_successful() {
        //Act
        assert(tariffService.getVehicles() == tariffRepository.getVehicles())
    }
}