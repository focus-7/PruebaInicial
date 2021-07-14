package com.ceiba.domain.repository

import com.ceiba.domain.aggregate.Tariff
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    suspend fun enterVehicle(tariff: Tariff)
    suspend fun takeOutVehicle(tariff: Tariff)
    fun getVehicles(): Flow<List<Tariff>>
}