package com.ceiba.application.service

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.service.TariffParkingService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VehicleApplicationService @Inject constructor(private var tariffParkingService: TariffParkingService) {
    suspend fun enterVehicle(tariff: Tariff) {
        tariffParkingService.enterVehicle(tariff)
    }

    suspend fun takeOutVehicle(tariff: Tariff) {
        tariffParkingService.takeOutVehicle(tariff)
    }

    fun getVehicles(): Flow<List<Tariff>> {
        return tariffParkingService.getVehicles()
    }
}