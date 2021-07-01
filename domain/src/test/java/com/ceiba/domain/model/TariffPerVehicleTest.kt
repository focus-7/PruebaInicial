package com.ceiba.domain.model

import com.ceiba.domain.builder.TariffObjectMother
import com.ceiba.domain.exception.InvalidDataException
import com.ceiba.domain.service.TariffCarService
import com.ceiba.domain.service.TariffMotorcycleService
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test


class TariffPerVehicleTest {
    private val tariffPerCar = TariffCarService()
    private val tariffPerMotorcycle = TariffMotorcycleService()


    @Test
    fun tariff_createTariffWithMotorcycle() {
        //Arrange
        val expected = TariffObjectMother.tariffOfMotorcycleCC150()

        //Assert
        assertNotNull(expected)
    }

    @Test
    fun tariff_createTariffWithCar() {
        //Arrange
        val expected = TariffObjectMother.tariffOfCar()

        //Assert
        assertNotNull(expected)
    }

    @Test
    fun tariff_createTariffWithCarOut() {
        //Arrange
        val tariffCar = TariffObjectMother.tariffOfCar()
        val expected = 4000.0
        TariffObjectMother.departureVehicleInJuneAtOnePm(tariffCar)

        //Act
        tariffCar.calculateVehicleTariff(tariffPerCar, tariffCar.vehicleDepartureDate)

        //Assert
        assertEquals(expected, tariffCar.amount)
    }

    @Test
    fun tariff_createTariffWithMotorcycleOutCylinder750() {
        //Arrange
        val tariffMotorcycle = TariffObjectMother.tariffOfMotorcycleCC750()
        val expected = 4000.0
        TariffObjectMother.departureVehicleInJuneAtEightPm(tariffMotorcycle)

        //Act
        tariffMotorcycle.calculateVehicleTariff(
            tariffPerMotorcycle,
            tariffMotorcycle.vehicleDepartureDate
        )

        //Assert
        assertEquals(expected, tariffMotorcycle.amount)
    }

    @Test
    fun tariff_createTariffWithMotorcycleOutCylinder150() {
        //Arrange
        val tariffMotorcycle = TariffObjectMother.tariffOfMotorcycleCC150()
        val expected = 4000.0
        TariffObjectMother.departureVehicleInJuneAtFivePm(tariffMotorcycle)

        //Act
        tariffMotorcycle.calculateVehicleTariff(
            tariffPerMotorcycle,
            tariffMotorcycle.vehicleDepartureDate
        )

        //Assert
        assertEquals(expected, tariffMotorcycle.amount)
    }


    @Test
    fun tariff_createTariffWithMotorcyclePlateInitANotCanEntry() {
        try {
            //Arrange
            TariffObjectMother.tariffOfMotorcycleOnTuesdayWithPlateA()
        } catch (ex: InvalidDataException) {
            //Assert
            assertEquals("Vehiculo no autorizado a ingresar", ex.message)
        }
    }

    @Test
    fun tariff_createTariffOfLessThanOneHourWithCar() {
        //Arrange
        val tariffCar = TariffObjectMother.tariffOfCar()
        val expected = 1000.0
        TariffObjectMother.departureVehicleInJune901am(tariffCar)

        //Act
        tariffCar.calculateVehicleTariff(tariffPerCar, tariffCar.vehicleDepartureDate)

        //Assert
        assertEquals(expected, tariffCar.amount)
    }

    @Test
    fun tariff_createTariffWithMotorcyclePlateInitACanEntryMonday() {
        //Arrange
        val expected = TariffObjectMother.tariffOfMotorcycleOnMondayWithPlateA()

        //Assert
        assertNotNull(expected)
    }

    @Test
    fun tariff_createTariffWithMotorcyclePlateInitACanEntrySunday() {
        //Arrange
        val expected = TariffObjectMother.tariffOfMotorcycleOnSundayWithPlateA()

        //Assert
        assertNotNull(expected)
    }

    @Test
    fun tariff_vehicleDepartureDateWithCar_successful() {
        //Arrange
        val tariffCar = TariffObjectMother.tariffOfCar()
        TariffObjectMother.departureVehicleInJuneAtOnePm(tariffCar)
        val expected = 4000.0

        //Act
        tariffCar.calculateVehicleTariff(tariffPerCar, tariffCar.vehicleDepartureDate)

        //Assert
        assertEquals(expected, tariffCar.amount)
    }

    @Test
    fun convert_motorcycleEntryDateString_successful() {
        //Arrange
        val motorcycle = TariffObjectMother.tariffOfMotorcycleCC150()
        val actual = TariffObjectMother.getStringDepartureDateVehicle(motorcycle.vehicleEntryDate)

        //Act
        val expected = motorcycle.getEntryDateString()

        //Assert
        assertEquals(expected, actual)
    }

    @Test
    fun convert_carDepartureDateString_successful() {
        //Arrange
        val car = TariffObjectMother.tariffOfCar()
        TariffObjectMother.departureVehicleInJuneAtOnePm(car)
        val actual = TariffObjectMother.getStringDepartureDateVehicle(car.vehicleDepartureDate)

        //Act
        val expected = car.getDepartureDateString()

        //Assert
        assertEquals(expected, actual)
    }
}