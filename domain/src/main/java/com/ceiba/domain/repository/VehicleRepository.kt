package com.ceiba.domain.repository

import com.ceiba.domain.aggregate.Tariff

interface VehicleRepository {
    fun enterCar(tariff: Tariff)
    fun enterMotorcycle(tariff: Tariff)
    fun takeOutVehicle(tariff: Tariff)
    fun getVehicles(): List<Tariff>
}