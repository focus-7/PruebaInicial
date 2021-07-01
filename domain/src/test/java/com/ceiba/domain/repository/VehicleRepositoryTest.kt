package com.ceiba.domain.repository

import com.ceiba.domain.builder.TariffObjectMother
import com.ceiba.domain.service.TariffCarService
import com.ceiba.domain.service.TariffMotorcycleService
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
        val motorcycle = TariffObjectMother.tariffOfMotorcycleCC150()

        //Act
        vehicleRepository.enterMotorcycle(motorcycle)
    }

    @Test
    fun enterCar_carWithCorrectParameters_successful() {
        //Arrange
        val car = TariffObjectMother.tariffOfCar()

        //Act
        vehicleRepository.enterCar(car)
    }

    @Test
    fun takeOutCar_WithCorrectParameters_successful() {
        //Arrange
        val tariffCar = TariffObjectMother.tariffOfCar()
        val tariffPerCar = TariffCarService()
        TariffObjectMother.departureVehicleInJuneAtOnePm(tariffCar)
        tariffCar.calculateVehicleTariff(tariffPerCar, tariffCar.vehicleDepartureDate)

        //Act
        vehicleRepository.takeOutVehicle(tariffCar)
    }

    @Test
    fun takeOutMotorcycle_WithCorrectParameters_successful() {
        //Arrange
        val tariffMotorcycle = TariffObjectMother.tariffOfMotorcycleCC150()
        val tariffPerMotorcycle = TariffMotorcycleService()
        TariffObjectMother.departureVehicleInJuneAtOnePm(tariffMotorcycle)
        tariffMotorcycle.calculateVehicleTariff(
            tariffPerMotorcycle,
            tariffMotorcycle.vehicleDepartureDate
        )

        //Act
        vehicleRepository.takeOutVehicle(tariffMotorcycle)
    }
}