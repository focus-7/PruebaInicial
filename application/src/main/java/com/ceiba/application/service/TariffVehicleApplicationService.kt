package com.ceiba.application.service

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.factory.VehicleFactory
import com.ceiba.domain.interfaceservice.TariffPerVehicle

class TariffVehicleApplicationService {
    fun calculateTariffVehicle(tariffOut: Tariff) {
        val vehicleFactory = VehicleFactory()
        val tariffPerVehicle: TariffPerVehicle = vehicleFactory.getVehicle(tariffOut.vehicleType)

        tariffOut.setTariffVehicle(tariffPerVehicle, tariffOut.vehicleDepartureDate)
    }
}