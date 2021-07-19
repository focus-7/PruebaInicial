package com.ceiba.domain.repository

import com.ceiba.domain.MainCoroutineRule
import com.ceiba.domain.builder.TariffObjectMother
import com.ceiba.domain.runBlockingTest
import com.ceiba.domain.service.TariffCarService
import com.ceiba.domain.service.TariffMotorcycleService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VehicleRepositoryTest {
    @Mock
    lateinit var vehicleRepository: VehicleRepository

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @ExperimentalCoroutinesApi
    @Test
    fun enterMotorcycle_motorcycleWithCorrectParameters_successful() = coroutineRule.runBlockingTest {
        //Arrange
        val motorcycle = TariffObjectMother.tariffOfMotorcycleCC150()

        //Act
        vehicleRepository.enterVehicle(motorcycle)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun enterCar_carWithCorrectParameters_successful() = coroutineRule.runBlockingTest {
        //Arrange
        val car = TariffObjectMother.tariffOfCar()

        //Act
        vehicleRepository.enterVehicle(car)
    }

    @ExperimentalCoroutinesApi
    @Test
     fun takeOutCar_WithCorrectParameters_successful() = coroutineRule.runBlockingTest {
        //Arrange
        val tariffCar = TariffObjectMother.tariffOfCar()
        val tariffPerCar = TariffCarService()
        TariffObjectMother.departureVehicleInJuneAtOnePm(tariffCar)
        tariffCar.setTariffVehicle(tariffPerCar, tariffCar.vehicleDepartureDate)

        //Act
        vehicleRepository.takeOutVehicle(tariffCar)
    }

    @ExperimentalCoroutinesApi
    @Test
     fun takeOutMotorcycle_WithCorrectParameters_successful() = coroutineRule.runBlockingTest {
        //Arrange
        val tariffMotorcycle = TariffObjectMother.tariffOfMotorcycleCC150()
        val tariffPerMotorcycle = TariffMotorcycleService()
        TariffObjectMother.departureVehicleInJuneAtOnePm(tariffMotorcycle)
        tariffMotorcycle.setTariffVehicle(tariffPerMotorcycle, tariffMotorcycle.vehicleDepartureDate)

        //Act
        vehicleRepository.takeOutVehicle(tariffMotorcycle)
    }
}