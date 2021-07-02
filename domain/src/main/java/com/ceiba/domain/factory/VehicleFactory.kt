package com.ceiba.domain.factory

import com.ceiba.domain.exception.InvalidDataException
import com.ceiba.domain.interfaceservice.TariffPerVehicle
import com.ceiba.domain.service.TariffCarService
import com.ceiba.domain.service.TariffMotorcycleService
import com.ceiba.domain.valueobject.VehicleType

class VehicleFactory {
    fun getVehicle(vehicle: Int): TariffPerVehicle {
        return when (vehicle) {
            VehicleType.CAR.type -> TariffCarService()
            VehicleType.MOTORCYCLE.type -> TariffMotorcycleService()
            else -> throw InvalidDataException("No se puede definir el tipo de vehiculo")
        }
    }
}