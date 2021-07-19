package com.ceiba.application.service

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.factory.VehicleFactory
import com.ceiba.domain.interfaceservice.TariffPerVehicle
import java.util.*

class TariffVehicleApplicationService {

    fun calculateTariffVehicle(tariffOut: Tariff) {
        try {
            val vehicleFactory = VehicleFactory()
            val tariffPerVehicle: TariffPerVehicle =
                vehicleFactory.getVehicle(tariffOut.vehicleType)
            val departureTime = Calendar.getInstance()
            tariffOut.setTariffVehicle(tariffPerVehicle, departureTime.timeInMillis)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}