package com.example.domain.entity

import com.example.domain.aggregate.Tariff
import com.example.domain.exception.InvalidDataException
import org.junit.Assert.*

import org.junit.Test

class TariffTest {

    @Test
    fun receipt_createTariffWithMotorcycle() {
        //Arrange
        val entryVehicle = 1624266000000 //June 21, 2021, 9:00 a.m
        val vehicle = Motorcycle("GER67E", 120)

        //Act
        val expected = Tariff(entryVehicle, vehicle)

        //Assert
        assertNotNull(expected)
    }

    @Test
    fun tariff_createTariffWithCar() {
        //Arrange
        val entryVehicle = 1624266000000 //June 21, 2021, 9:00 a.m
        val vehicle = Car("MSK456")

        //Act
        val expected = Tariff(entryVehicle, vehicle)

        //Assert
        assertNotNull(expected)
    }

    @Test
    fun tariff_createTariffWithCarOut() {
        //Arrange
        val entryVehicle = 1624266000000 //June 21, 2021, 9:00 a.m
        val vehicle = Car("MSK456")

        //Act
        val expected = Tariff(entryVehicle, vehicle)
        expected.departureDate = 1624366800000 //June 22, 2021, 1:00 p.m

        //Assert
        assertEquals(12000.0, expected.amount)
    }

    @Test
    fun tariff_createTariffWithMotorcycleOutCylinder750() {
        //Arrange
        val entryVehicle = 1624266000000 //June 21, 2021, 9:00 a.m
        val vehicle = Motorcycle("GER67E", 750)

        //Act
        val expected = Tariff(entryVehicle, vehicle)
        expected.departureDate = 1624305600000 //June 21, 2021, 8:00 p.m

        //Assert
        assertEquals(6000.0, expected.amount)
    }

    @Test
    fun tariff_createTariffWithMotorcycleOutCylinder150() {
        //Arrange
        val entryVehicle = 1624266000000 //June 21, 2021, 9:00 a.m
        val vehicle = Motorcycle("GER67E", 150)

        //Act
        val expected = Tariff(entryVehicle, vehicle)
        expected.departureDate = 1624305600000 //June 21, 2021, 8:00 p.m

        //Assert
        assertEquals(4000.0, expected.amount)
    }

    @Test
    fun tariff_createTariffWithMotorcyclePlateInitANotCanEntry() {
        //Arrange
        val entryVehicle = 1624352400000 //June 22, 2021, 9:00 a.m
        val vehicle = Motorcycle("AER67E", 150)

        try {
            //Act
            Tariff(entryVehicle, vehicle)
        } catch (ex: InvalidDataException) {
            //Assert
            assertEquals("Vehiculo no autorizado a ingresar", ex.message)
        }
    }

    @Test
    fun receipt_createReceiptWithMotorcyclePlateInitACanEntryMonday() {
        //Arrange
        val entryVehicle = 1623661200000 //June 14, 2021, 9:00 a.m (Monday)
        val vehicle = Motorcycle("AER67E", 120)

        //Act
        val expected = Tariff(entryVehicle, vehicle)

        //Assert
        assertNotNull(expected)
    }

    @Test
    fun receipt_createReceiptWithMotorcyclePlateInitACanEntrySunday() {
        //Arrange
        val entryVehicle = 1624179600000 //June 20, 2021, 9:00 a.m (Sunday)
        val vehicle = Motorcycle("AER67E", 120)

        //Act
        val expected = Tariff(entryVehicle, vehicle)

        //Assert
        assertNotNull(expected)
    }
}