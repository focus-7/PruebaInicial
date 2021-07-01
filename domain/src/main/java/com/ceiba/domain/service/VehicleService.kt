package com.ceiba.domain.service

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.exception.InvalidDataException
import com.ceiba.domain.repository.VehicleRepository
import javax.inject.Inject

class VehicleService @Inject constructor(
    private val vehicleRepository: VehicleRepository,
    private val parkingService: ParkingService,
) {
    fun enterCar(tariff: Tariff): Boolean {
        if (parkingService.checkAvailableSpaceForCars()) {
            vehicleRepository.enterCar(tariff)
        } else {
            throw InvalidDataException("No hay campo disponible para el vehículo.")
        }
        return true
    }

    fun enterMotorcycle(tariff: Tariff): Boolean {
        if (parkingService.checkAvailableSpaceForMotorcycles()) {
            vehicleRepository.enterMotorcycle(tariff)
        } else {
            throw InvalidDataException("No hay campo disponible para el vehículo.")
        }
        return true
    }

    fun takeOutVehicle(tariff: Tariff): Boolean {
        if (tariff.amount != null) {
            vehicleRepository.takeOutVehicle(tariff)
            return true
        } else {
            throw InvalidDataException("No se logro calcular el pago.")
        }
    }

    fun getVehicles(): List<Tariff> {
        return vehicleRepository.getVehicles()
    }
}