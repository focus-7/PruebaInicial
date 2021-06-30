package com.ceiba.domain.repository

import com.ceiba.domain.aggregate.Tariff

interface ParkingRepository {
    fun getQuantityOfVehicles(typeVehicle: Int): Int
    fun searchVehicleByPlate(plate: String): List<Tariff>
}