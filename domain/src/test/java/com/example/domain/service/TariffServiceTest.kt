package com.example.domain.service

import com.example.domain.aggregate.Tariff
import com.example.domain.entity.Car
import com.example.domain.entity.Motorcycle
import com.example.domain.exception.InvalidDataException
import com.example.domain.repository.TariffRepository
import org.junit.Assert
import org.junit.Assert.assertNotNull
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
    fun enterVehicle_withCorrectParameters_successful() {
        //Arrange
        val entryVehicle = 1624352400000 //June 22, 2021, 9:00 a.m
        val vehicle = Motorcycle("TYU78E", 150)

        //Assert
        assertTrue(tariffService.enterVehicle(entryVehicle, vehicle))
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
        assertTrue(tariffService.takeOutVehicle(car))
    }

    @Test
    fun takeOut_vehicleWithNullParameters_failure() {
        //Arrange
        val entryVehicle = 1624266000000 //June 21, 2021, 9:00 a.m
        val vehicle = Car("TYU78E")
        val car = Tariff(entryVehicle, vehicle)

        try {
            //Act
            tariffService.takeOutVehicle(car)
        } catch (ex: InvalidDataException) {
            //Assert
            Assert.assertEquals("No se logro calcular el pago.", ex.message)
        }
    }

    @Test
    fun get_vehiclesByPlate_successful() {
        //Arrange
        val vehicle = Car("TYU78E")

        //Act
        assertNotNull(tariffService.getVehiclesByPlate(vehicle.plate))
    }

    @Test
    fun get_allVehicles_successful() {
        //Act
        assertNotNull(tariffService.getVehicles())
    }
}
