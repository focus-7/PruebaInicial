package com.ceiba.domain.service

import com.ceiba.domain.MainCoroutineRule
import com.ceiba.domain.builder.TariffObjectMother
import com.ceiba.domain.exception.InvalidDataException
import com.ceiba.domain.repository.ParkingRepository
import com.ceiba.domain.repository.VehicleRepository
import com.ceiba.domain.runBlockingTest
import com.ceiba.domain.valueobject.VehicleType
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOn
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import kotlin.time.ExperimentalTime


class TariffParkingServiceTest {
    @Mock
    lateinit var vehicleRepository: VehicleRepository

    @Mock
    lateinit var parkingRepository: ParkingRepository

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var searchVehicleService: SearchVehicleService

    private lateinit var tariffParkingService: TariffParkingService

    @Before
    fun setup() {
        initMocks(this)
        tariffParkingService = TariffParkingService(vehicleRepository, parkingRepository)
        searchVehicleService = SearchVehicleService(parkingRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun enterVehicle_withCorrectParameters_successful() = coroutineRule.runBlockingTest {
        //Arrange
        val tariffMotorcycle = TariffObjectMother.tariffOfMotorcycleCC150()

        `when`(parkingRepository.getCountVehiclesByType(VehicleType.MOTORCYCLE.type)).thenReturn(1)

        //Assert
        assertTrue(tariffParkingService.enterVehicle(tariffMotorcycle))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun enterVehicle_noAvailableSpaceForMotorcycle_successful() = coroutineRule.runBlockingTest {
        //Arrange
        val tariffMotorcycle = TariffObjectMother.tariffOfMotorcycleCC150()
        `when`(parkingRepository.getCountVehiclesByType(VehicleType.MOTORCYCLE.type)).thenReturn(11)

        try {
            //Act
            tariffParkingService.enterVehicle(tariffMotorcycle)
        } catch (ex: InvalidDataException) {
            //Assert
            assertEquals("No hay campo disponible para el vehículo.", ex.message)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun enterVehicle_noAvailableSpaceForCar_successful() = coroutineRule.runBlockingTest {
        //Arrange
        val tariffCar = TariffObjectMother.tariffOfCar()
        `when`(parkingRepository.getCountVehiclesByType(VehicleType.CAR.type)).thenReturn(21)

        try {
            //Act
            tariffParkingService.enterVehicle(tariffCar)
        } catch (ex: InvalidDataException) {
            //Assert
            assertEquals("No hay campo disponible para el vehículo.", ex.message)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun takeOut_vehicleWithCorrectParameters_successful() = coroutineRule.runBlockingTest {
        //Arrange
        val tariffCar = TariffObjectMother.tariffOfCar()
        val tariffPerCar = TariffCarService()
        TariffObjectMother.departureVehicleInJuneAtOnePm(tariffCar)

        //Act
        tariffCar.setTariffVehicle(tariffPerCar, tariffCar.vehicleDepartureDate)

        //Assert
        assertTrue(tariffParkingService.takeOutVehicle(tariffCar))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun takeOut_vehicleWithNullParameters_failure() = coroutineRule.runBlockingTest {
        //Arrange
        val tariffCar = TariffObjectMother.tariffOfCar()
        TariffObjectMother.departureVehicleInJuneAtOnePm(tariffCar)

        try {
            //Act
            tariffParkingService.takeOutVehicle(tariffCar)
        } catch (ex: InvalidDataException) {
            //Assert
            assertEquals("No se logro calcular el pago.", ex.message)
        }
    }

    @ExperimentalTime
    @ExperimentalCoroutinesApi
    @Test
    fun get_vehiclesByPlate_successful() = coroutineRule.runBlockingTest {
        //Arrange
        val car = TariffObjectMother.vehicleTypeCar()

        //Act
        assertNotNull(searchVehicleService.getVehiclesByPlate(car.plate).flowOn(IO))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun get_allVehicles_successful() = coroutineRule.runBlockingTest {
        //Act
        assertNotNull(tariffParkingService.getVehicles().flowOn(IO))
    }
}