package com.ceiba.application.service

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.service.TariffParkingService
import javax.inject.Inject

class VehicleApplicationService @Inject constructor(private var tariffParkingService: TariffParkingService) {
    fun enterVehicle(tariff: Tariff) {
        tariffParkingService.enterVehicle(tariff)
    }

    fun takeOutVehicle(tariff: Tariff) {
        tariffParkingService.takeOutVehicle(tariff)
    }

    fun getVehicles(): List<Tariff> {
        return tariffParkingService.getVehicles()
    }
}