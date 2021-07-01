package com.ceiba.domain.service

import com.ceiba.domain.builder.TariffObjectMother
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

    private val tariffPerCar = TariffPerVehicleCarService()
    private val tariffPerMotorcycle = TariffPerVehicleMotorcycleService()

    @Test
    fun enterVehicle_withCorrectParameters_successful() {
        //Arrange
        val tariffMotorcycle = TariffObjectMother.tariffOfMotorcycleCC150()

        //Assert
        assertTrue(vehicleService.enterVehicle(tariffMotorcycle))
    }

    @Test
    fun enterVehicle_noAvailableSpaceForMotorcycle_successful() {
        //Arrange
        val tariffMotorcycle = TariffObjectMother.tariffOfMotorcycleCC150()
        `when`(parkingRepository.getQuantityOfVehicles(2)).thenReturn(11)

        try {
            //Act
            vehicleService.enterVehicle(tariffMotorcycle)
        } catch (ex: InvalidDataException) {
            //Assert
            assertEquals("No hay campo disponible para el vehículo.", ex.message)
        }
    }

    @Test
    fun enterVehicle_noAvailableSpaceForCar_successful() {
        //Arrange
        val tariffCar = TariffObjectMother.tariffOfCar()
        `when`(parkingRepository.getQuantityOfVehicles(1)).thenReturn(21)

        try {
            //Act
            vehicleService.enterVehicle(tariffCar)
        } catch (ex: InvalidDataException) {
            //Assert
            assertEquals("No hay campo disponible para el vehículo.", ex.message)
        }
    }

    @Test
    fun takeOut_vehicleWithCorrectParameters_successful() {
        //Arrange
        val tariffCar = TariffObjectMother.tariffOfCar()
        TariffObjectMother.departureVehicleInJuneAtOnePm(tariffCar)

        //Act
        tariffCar.calculateVehicleTariff(tariffPerCar, tariffCar.vehicleDepartureDate)

        //Assert
        assertTrue(vehicleService.takeOutVehicle(tariffCar))
    }

    @Test
    fun takeOut_vehicleWithNullParameters_failure() {
        //Arrange
        val tariffCar = TariffObjectMother.tariffOfCar()
        TariffObjectMother.departureVehicleInJuneAtOnePm(tariffCar)

        try {
            //Act
            vehicleService.takeOutVehicle(tariffCar)
        } catch (ex: InvalidDataException) {
            //Assert
            assertEquals("No se logro calcular el pago.", ex.message)
        }
    }

    @Test
    fun get_vehiclesByPlate_successful() {
        //Arrange
        val car = TariffObjectMother.vehicleTypeCar()

        //Act
        assertNotNull(parkingService.getVehiclesByPlate(car.plate))
    }

    @Test
    fun get_allVehicles_successful() {
        //Act
        assertNotNull(vehicleService.getVehicles())
    }
}
