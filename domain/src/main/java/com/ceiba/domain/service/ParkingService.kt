package com.ceiba.domain.service

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.exception.InvalidDataException
import com.ceiba.domain.repository.ParkingRepository
import com.ceiba.domain.valueobject.VehicleType
import javax.inject.Inject

class ParkingService @Inject constructor(
    private val parkingRepository: ParkingRepository
) {
    companion object {
        const val MAX_CANT_CAR = 20
        const val MAX_CANT_MOTORCYCLE = 10
    }

    fun getVehiclesByPlate(plate: String): List<Tariff> {
        return parkingRepository.searchVehicleByPlate(plate)
    }

    fun checkAvailableVehicleSpace(tariff: Tariff): Boolean {
        return when (tariff.vehicleType) {
            VehicleType.CAR.type -> (parkingRepository.getQuantityOfVehicles(tariff.vehicleType) < MAX_CANT_CAR)
            VehicleType.MOTORCYCLE.type -> (parkingRepository.getQuantityOfVehicles(tariff.vehicleType) < MAX_CANT_MOTORCYCLE)
            else -> throw InvalidDataException("No hay campo disponible para el vehículo.")
        }
    }
}