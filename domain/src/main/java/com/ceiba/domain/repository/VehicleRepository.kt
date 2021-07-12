package com.ceiba.domain.repository

import com.ceiba.domain.aggregate.Tariff
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    fun enterVehicle(tariff: Tariff)
    fun takeOutVehicle(tariff: Tariff)
    fun getVehicles(): Flow<List<Tariff>>
}