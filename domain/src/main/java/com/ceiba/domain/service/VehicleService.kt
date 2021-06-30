package com.ceiba.domain.service

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.model.Vehicle
import com.ceiba.domain.exception.InvalidDataException
import com.ceiba.domain.repository.ParkingRepository
import com.ceiba.domain.repository.VehicleRepository
import com.ceiba.domain.valueobject.VehicleType
import javax.inject.Inject

class VehicleService @Inject constructor(
    private val vehicleRepository: VehicleRepository,
    private val parkingRepository: ParkingRepository
) {
    fun enterVehicle(entryDate: Long, vehicle: Vehicle): Boolean {
        val tariff = Tariff(entryDate, vehicle)
        if (checkAvailableVehicleSpace(tariff)) {
            vehicleRepository.enterVehicle(tariff)
            return true
        } else {
            throw InvalidDataException("No hay campo disponible para el vehículo.")
        }
    }

    private fun checkAvailableVehicleSpace(tariff: Tariff): Boolean {
        return when (tariff.vehicleType) {
            VehicleType.CAR.type -> (parkingRepository.getQuantityOfVehicles(tariff.vehicleType) < ParkingService.MAX_CANT_CAR)
            VehicleType.MOTORCYCLE.type -> (parkingRepository.getQuantityOfVehicles(tariff.vehicleType) < ParkingService.MAX_CANT_MOTORCYCLE)
            else -> throw InvalidDataException("No hay campo disponible para el vehículo.")
        }
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