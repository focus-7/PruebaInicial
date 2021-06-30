package com.ceiba.domain.repository

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.model.Car
import com.ceiba.domain.model.Motorcycle
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VehicleRepositoryTest {

    @Mock
    lateinit var vehicleRepository: VehicleRepository

    @Test
    fun enterMotorcycle_motorcycleWithCorrectParameters_successful() {
        //Arrange
        val entryVehicle = 1624352400000 //June 22, 2021, 9:00 a.m
        val vehicle = Motorcycle("TYU78E", 150)
        val motorcycle = Tariff(entryVehicle, vehicle)

        //Act
        vehicleRepository.enterVehicle(motorcycle)
    }

    @Test
    fun enterCar_carWithCorrectParameters_successful() {
        //Arrange
        val entryVehicle = 1624352400000 //June 22, 2021, 9:00 a.m
        val vehicle = Car("MSK381")
        val car = Tariff(entryVehicle, vehicle)

        //Act
        vehicleRepository.enterVehicle(car)
    }

    @Test
    fun takeOutCar_WithCorrectParameters_successful() {
        //Arrange
        val entryVehicle = 1624266000000 //June 21, 2021, 9:00 a.m
        val vehicle = Car("TYU78E")
        val car = Tariff(entryVehicle, vehicle)
        car.vehicleDepartureDate = 1624302000000 //June 22, 2021, 7:00 p.m

        //Act
        vehicleRepository.takeOutVehicle(car)
    }

    @Test
    fun takeOutMotorcycle_WithCorrectParameters_successful() {
        //Arrange
        val entryVehicle = 1624266000000 //June 21, 2021, 9:00 a.m
        val vehicle = Motorcycle("TYU78E", 150)
        val motorcycle = Tariff(entryVehicle, vehicle)
        motorcycle.vehicleDepartureDate = 1624302000000 //June 22, 2021, 7:00 p.m

        //Act
        vehicleRepository.takeOutVehicle(motorcycle)
    }
}