package com.example.domain.service

import com.example.domain.aggregate.Tariff
import com.example.domain.entity.Car
import com.example.domain.entity.Motorcycle
import com.example.domain.entity.Vehicle
import com.example.domain.exception.InvalidDataException
import com.example.domain.repository.TariffRepository
import javax.inject.Inject

class TariffService @Inject constructor(private val tariffRepository: TariffRepository) {
    companion object {
        const val MAX_CANT_CAR = 20
        const val MAX_CANT_MOTORCYCLE = 10
    }

    private fun checkAvailableVehicleSpace(vehicle: Vehicle): Boolean {
        return when (vehicle) {
            is Car -> (tariffRepository.getQuantityOfVehicles(1) < MAX_CANT_CAR)
            is Motorcycle -> (tariffRepository.getQuantityOfVehicles(2) < MAX_CANT_MOTORCYCLE)
            else -> throw InvalidDataException("No hay campo disponible para el vehículo.")
        }
    }

    fun enterVehicle(entryDate: Long, vehicle: Vehicle): Boolean {
        val tariff = Tariff(entryDate, vehicle)
        if (checkAvailableVehicleSpace(vehicle)) {
            tariffRepository.enterVehicle(tariff)
            return true
        } else {
            throw InvalidDataException("No hay campo disponible para el vehículo.")
        }
    }

    fun takeOutVehicle(tariff: Tariff): Boolean {
        if (tariff.amount != null) {
            tariffRepository.takeOutVehicle(tariff)
            return true
        } else {
            throw InvalidDataException("No se logro calcular el pago.")
        }
    }


    fun getVehicles(): List<Tariff> {
        return tariffRepository.getVehicles()
    }

    fun getVehiclesByPlate(plate: String): List<Tariff> {
        return tariffRepository.searchVehicleByPlate(plate)
    }
}