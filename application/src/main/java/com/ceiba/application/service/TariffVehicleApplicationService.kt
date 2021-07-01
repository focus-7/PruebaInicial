package com.ceiba.application.service

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.service.TariffCarService
import com.ceiba.domain.service.TariffMotorcycleService
import com.ceiba.domain.service.TariffPerVehicle
import com.ceiba.domain.valueobject.VehicleType

class TariffVehicleApplicationService {
    fun calculateTariffVehicle(tariffOut: Tariff) {
        var tariffPerVehicle: TariffPerVehicle
        if (tariffOut.vehicleType == VehicleType.CAR.type) {
            tariffPerVehicle = TariffCarService()
            tariffOut.calculateVehicleTariff(tariffPerVehicle, tariffOut.vehicleDepartureDate)
        }
        if (tariffOut.vehicleType == VehicleType.MOTORCYCLE.type) {
            tariffPerVehicle = TariffMotorcycleService()
            tariffOut.calculateVehicleTariff(tariffPerVehicle, tariffOut.vehicleDepartureDate)
        }
    }
}