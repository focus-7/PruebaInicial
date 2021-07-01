package com.ceiba.application.service

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.service.VehicleService
import com.ceiba.domain.valueobject.VehicleType
import javax.inject.Inject

class VehicleApplicationService @Inject constructor(private var vehicleService: VehicleService) {
    fun enterVehicle(tariff: Tariff) {
        if (tariff.vehicleType == VehicleType.CAR.type) {
            vehicleService.enterCar(tariff)
        } else {
            vehicleService.enterMotorcycle(tariff)
        }
    }

    fun takeOutVehicle(tariff: Tariff) {
        vehicleService.takeOutVehicle(tariff)
    }

    fun getVehicles(): List<Tariff> {
        return vehicleService.getVehicles()
    }
}