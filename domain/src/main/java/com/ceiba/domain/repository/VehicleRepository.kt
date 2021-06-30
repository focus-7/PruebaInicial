package com.ceiba.domain.repository

import com.ceiba.domain.aggregate.Tariff

interface VehicleRepository {
    fun takeOutVehicle(tariff: Tariff)
    fun enterVehicle(tariff: Tariff)
    fun getVehicles(): List<Tariff>
}