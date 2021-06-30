package com.ceiba.domain.model

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.builder.TariffObjectMother
import com.ceiba.domain.exception.InvalidDataException
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*


class TariffTest {

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
        val expected = TariffObjectMother.tariffOfCar()

        //Act
        TariffObjectMother.departureVehicleInJuneAtOnePm(expected)

        //Assert
        assertEquals(12000.0, expected.amount)
    }

    @Test
    fun tariff_createTariffWithMotorcycleOutCylinder750() {
        //Arrange
        val expected = TariffObjectMother.tariffOfMotorcycleCC750()

        //Act
        TariffObjectMother.departureVehicleInJuneAtEightPm(expected)

        //Assert
        assertEquals(6000.0, expected.amount)
    }

    @Test
    fun tariff_createTariffWithMotorcycleOutCylinder150() {
        //Arrange
        val expected = TariffObjectMother.tariffOfMotorcycleCC150()

        //Act
        TariffObjectMother.departureVehicleInJuneAtEightPm(expected)

        //Assert
        assertEquals(4000.0, expected.amount)
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
        val expected = TariffObjectMother.tariffOfCar()

        //Act
        TariffObjectMother.departureVehicleInJune901am(expected)

        //Assert
        assertEquals(1000.0, expected.amount)
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

 /*   @Test
    fun tariff_vehicleDepartureDateWithCar_successful() {
        //Arrange
        val date = Date(1624366800000) //June 22, 2021, 1:00 p.m
        val vehicle = Car("TYU789")
        val car = Tariff(1624352400000, vehicle)  //June 22, 2021, 9:00 a.m

        //Act
        car.vehicleDepartureDate = date.time

        //Assert
        assertNotNull(car.vehicleDepartureDate)
    }*/

    @Test
    fun convert_motorcycleEntryDateString_successful() {
        //Arrange
        val date = Date(1624352400000) //June 22, 2021, 9:00 a.m
        val format = SimpleDateFormat("dd/MM/yyyy HH:mm a", Locale.getDefault())
        val vehicle = Motorcycle("TYU78E", 150)
        val motorcycle = Tariff(date.time, vehicle)

        //Act
        val expected = motorcycle.getEntryDateString()

        //Assert
        assertEquals(format.format(date), expected)
    }

    @Test
    fun convert_carDepartureDateString_successful() {
        //Arrange
        val date = Date(1624366800000) //June 22, 2021, 1:00 p.m
        val format = SimpleDateFormat("dd/MM/yyyy HH:mm a", Locale.getDefault())
        val vehicle = Car("TYU789")
        val car = Tariff(1624352400000, vehicle)  //June 22, 2021, 9:00 a.m

        //Act
        car.vehicleDepartureDate = date.time
        val expected = car.getDepartureDateString()

        //Assert
        assertEquals(format.format(date), expected)
    }
}