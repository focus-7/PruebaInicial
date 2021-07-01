package com.ceiba.domain.repository

import com.ceiba.domain.aggregate.Tariff

interface ParkingRepository {
    fun getQuantityOfCars(): Int
    fun getQuantityOfMotorcycles(): Int
    fun searchVehicleByPlate(plate: String): List<Tariff>
}