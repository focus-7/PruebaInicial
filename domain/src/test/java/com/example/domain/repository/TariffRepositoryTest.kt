package com.example.domain.repository

import com.example.domain.aggregate.Tariff
import com.example.domain.entity.Car
import com.example.domain.entity.Motorcycle
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TariffRepositoryTest {

    @Mock
    lateinit var tariffRepository: TariffRepository

    @Test
    fun enterMotorcycle_motorcycleWithCorrectParameters_successful() {
        //Arrange
        val entryVehicle = 1624352400000 //June 22, 2021, 9:00 a.m
        val vehicle = Motorcycle("TYU78E", 150)
        val motorcycle = Tariff(entryVehicle, vehicle)

        //Act
        tariffRepository.enterVehicle(motorcycle)
    }

    @Test
    fun enterCar_carWithCorrectParameters_successful() {
        //Arrange
        val entryVehicle = 1624352400000 //June 22, 2021, 9:00 a.m
        val vehicle = Car("MSK381")
        val car = Tariff(entryVehicle, vehicle)

        //Act
        tariffRepository.enterVehicle(car)
    }

    @Test
    fun takeOut_vehicleWithCorrectParameters_successful() {
        //Arrange
        val entryVehicle = 1624266000000 //June 21, 2021, 9:00 a.m
        val vehicle = Car("TYU78E")
        val motorcycle = Tariff(entryVehicle, vehicle)
        motorcycle.vehicleDepartureDate = 1624366800000 //June 22, 2021, 1:00 p.m

        //Act
        tariffRepository.takeOutVehicle(motorcycle)
    }
}