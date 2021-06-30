package com.ceiba.domain.service

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.model.Car
import com.ceiba.domain.model.Motorcycle
import com.ceiba.domain.exception.InvalidDataException
import com.ceiba.domain.repository.ParkingRepository
import com.ceiba.domain.repository.VehicleRepository
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class VehicleServiceTest {
    @Mock
    lateinit var vehicleRepository: VehicleRepository

    @Mock
    lateinit var parkingRepository: ParkingRepository

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @InjectMocks
    lateinit var vehicleService: VehicleService

    @InjectMocks
    lateinit var parkingService: ParkingService

    @Test
    fun enterVehicle_withCorrectParameters_successful() {
        //Arrange
        val entryVehicle = 1624352400000 //June 22, 2021, 9:00 a.m
        val vehicle = Motorcycle("TYU78E", 150)

        //Assert
        assertTrue(vehicleService.enterVehicle(entryVehicle, vehicle))
    }

    @Test
    fun enterVehicle_noAvailableSpaceForMotorcycle_successful() {
        //Arrange
        val entryVehicle = 1624352400000 //June 22, 2021, 9:00 a.m
        val vehicle = Motorcycle("TYU78E", 150)
        `when`(parkingRepository.getQuantityOfVehicles(2)).thenReturn(11)

        try {
            //Act
            vehicleService.enterVehicle(entryVehicle, vehicle)
        } catch (ex: InvalidDataException) {
            //Assert
            assertEquals("No hay campo disponible para el vehículo.", ex.message)
        }
    }

    @Test
    fun enterVehicle_noAvailableSpaceForCar_successful() {
        //Arrange
        val entryVehicle = 1624352400000 //June 22, 2021, 9:00 a.m
        val vehicle = Car("TYU786")
        `when`(parkingRepository.getQuantityOfVehicles(1)).thenReturn(21)

        try {
            //Act
            vehicleService.enterVehicle(entryVehicle, vehicle)
        } catch (ex: InvalidDataException) {
            //Assert
            assertEquals("No hay campo disponible para el vehículo.", ex.message)
        }
    }

    @Test
    fun takeOut_vehicleWithCorrectParameters_successful() {
        //Arrange
        val entryVehicle = 1624266000000 //June 21, 2021, 9:00 a.m
        val vehicle = Car("TYU78E")
        val car = Tariff(entryVehicle, vehicle)

        //Act
        car.vehicleDepartureDate = 1624366800000 //June 22, 2021, 1:00 p.m

        //Assert
        assertTrue(vehicleService.takeOutVehicle(car))
    }

    @Test
    fun takeOut_vehicleWithNullParameters_failure() {
        //Arrange
        val entryVehicle = 1624266000000 //June 21, 2021, 9:00 a.m
        val vehicle = Car("TYU78E")
        val car = Tariff(entryVehicle, vehicle)

        try {
            //Act
            vehicleService.takeOutVehicle(car)
        } catch (ex: InvalidDataException) {
            //Assert
            assertEquals("No se logro calcular el pago.", ex.message)
        }
    }

    @Test
    fun get_vehiclesByPlate_successful() {
        //Arrange
        val vehicle = Car("TYU78E")

        //Act
        assertNotNull(parkingService.getVehiclesByPlate(vehicle.plate))
    }

    @Test
    fun get_allVehicles_successful() {
        //Act
        assertNotNull(vehicleService.getVehicles())
    }
}
