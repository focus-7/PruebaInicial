package com.ceiba.application.service

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.service.VehicleService
import javax.inject.Inject

class VehicleApplicationService @Inject constructor(private var vehicleService: VehicleService) {
    fun enterCar(tariff: Tariff) {
        vehicleService.enterCar(tariff)
    }

    fun enterMotorcycle(tariff: Tariff) {
        vehicleService.enterMotorcycle(tariff)
    }

    fun takeOutVehicle(tariff: Tariff) {
        vehicleService.takeOutVehicle(tariff)
    }

    fun getVehicles(): List<Tariff> {
        return vehicleService.getVehicles()
    }
}