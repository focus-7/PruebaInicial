package com.ceiba.application.service

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.service.TariffParkingService
import javax.inject.Inject

class VehicleApplicationService @Inject constructor(private var tariffParkingService: TariffParkingService) {
    suspend fun enterVehicle(tariff: Tariff) {
        try {
            tariffParkingService.enterVehicle(tariff)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun takeOutVehicle(tariff: Tariff) {
        try {
            tariffParkingService.takeOutVehicle(tariff)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getVehicles() = tariffParkingService.getVehicles()
}