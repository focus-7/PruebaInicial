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
    fun takeOutCar_WithCorrectParameters_successful() {
        //Arrange
        val entryVehicle = 1624266000000 //June 21, 2021, 9:00 a.m
        val vehicle = Car("TYU78E")
        val car = Tariff(entryVehicle, vehicle)
        car.vehicleDepartureDate = 1624302000000 //June 22, 2021, 7:00 p.m

        //Act
        tariffRepository.takeOutVehicle(car)
    }

    @Test
    fun takeOutMotorcycle_WithCorrectParameters_successful() {
        //Arrange
        val entryVehicle = 1624266000000 //June 21, 2021, 9:00 a.m
        val vehicle = Motorcycle("TYU78E", 150)
        val motorcycle = Tariff(entryVehicle, vehicle)
        motorcycle.vehicleDepartureDate = 1624302000000 //June 22, 2021, 7:00 p.m

        //Act
        tariffRepository.takeOutVehicle(motorcycle)
    }
}