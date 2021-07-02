package com.ceiba.domain.repository

import com.ceiba.domain.aggregate.Tariff

interface ParkingRepository {
    fun getCountVehiclesByType(type: Int): Int
    fun searchVehicleByPlate(plate: String): List<Tariff>
}