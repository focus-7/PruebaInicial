package com.example.domain.service

import com.example.domain.aggregate.Tariff
import com.example.domain.entity.Car
import com.example.domain.entity.Motorcycle
import com.example.domain.entity.Vehicle
import com.example.domain.exception.CalculateAmountException
import com.example.domain.exception.MaximumVehicleCapacityException
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
            else -> throw MaximumVehicleCapacityException()
        }
    }

    fun enterVehicle(entryDate: Long, vehicle: Vehicle) {
        val tariff = Tariff(entryDate, vehicle)
        if (checkAvailableVehicleSpace(vehicle)) {
            tariffRepository.enterVehicle(tariff)
        } else {
            throw MaximumVehicleCapacityException()
        }
    }

    fun takeOutVehicle(tariff: Tariff) {
        if (tariff.amount != null) {
            tariffRepository.takeOutVehicle(tariff)
        } else {
            throw CalculateAmountException()
        }
    }


    fun getVehicles(): List<Tariff> {
        return tariffRepository.getVehicles()
    }

    fun getVehiclesByPlate(plate: String): List<Tariff> {
        return tariffRepository.searchVehicleByPlate(plate)
    }
}