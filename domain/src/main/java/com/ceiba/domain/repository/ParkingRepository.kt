package com.ceiba.domain.repository

import com.ceiba.domain.aggregate.Tariff
import kotlinx.coroutines.flow.Flow

interface ParkingRepository {
    suspend fun getCountVehiclesByType(type: Int): Int
    fun searchVehicleByPlate(plate: String): Flow<List<Tariff>>
}