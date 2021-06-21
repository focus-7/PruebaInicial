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

    fun enterVehicle(entryDate: Long, vehicle: Vehicle): String {
        val tariff = Tariff(entryDate, vehicle)
        if (checkAvailableVehicleSpace(vehicle)) {
            tariffRepository.enterVehicle(tariff)
            return "¡Coche aparcado con éxito!"
        } else {
            throw MaximumVehicleCapacityException()
        }
    }

    fun takeOutVehicle(departureDate: Long, tariff: Tariff): Double {
        tariff.departureDate = departureDate
        return tariff.amount ?: throw CalculateAmountException()
    }


    fun getVehicle(): List<Tariff> {
        return tariffRepository.getVehicles()
    }
}