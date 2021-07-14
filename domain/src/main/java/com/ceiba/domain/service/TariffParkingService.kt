package com.ceiba.domain.service

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.exception.InvalidDataException
import com.ceiba.domain.factory.VehicleFactory
import com.ceiba.domain.interfaceservice.TariffPerVehicle
import com.ceiba.domain.repository.ParkingRepository
import com.ceiba.domain.repository.VehicleRepository
import kotlinx.coroutines.flow.Flow
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

class TariffParkingService @Inject constructor(
    private val vehicleRepository: VehicleRepository,
    private val parkingRepository: ParkingRepository,
) {
    companion object {
        const val PLATE_INITIAL_LETTER = 'A'
        const val PLATE_PATTERN_STRING = "^[A-Z]{3}[A-Z 0-9]{3}\$"
    }

    suspend fun enterVehicle(tariff: Tariff): Boolean {
        if (checkAvailableSpaceForVehicles(tariff) && validateEntryDateVehicle(tariff)
            && validatePlateFormat(tariff.vehicle.plate)
        ) {
            vehicleRepository.enterVehicle(tariff)
            return true
        } else {
            throw InvalidDataException("No hay campo disponible para el vehículo.")
        }
    }

    suspend fun takeOutVehicle(tariff: Tariff): Boolean {
        if (tariff.amount != null) {
            vehicleRepository.takeOutVehicle(tariff)
            return true
        } else {
            throw InvalidDataException("No se logro calcular el pago.")
        }
    }

    fun getVehicles(): Flow<List<Tariff>> {
        return vehicleRepository.getVehicles()
    }

    private fun validatePlateFormat(plate: String): Boolean {
        val pattern: Pattern = Pattern.compile(PLATE_PATTERN_STRING)
        val matcher: Matcher = pattern.matcher(plate)
        return matcher.matches()
    }

    private fun validateEntryDateVehicle(tariff: Tariff): Boolean {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = tariff.entryDate
        val day = calendar.get(Calendar.DAY_OF_WEEK)
        if (tariff.vehicle.plate[0] == PLATE_INITIAL_LETTER && day != Calendar.SUNDAY && day != Calendar.MONDAY)
            throw InvalidDataException("Vehiculo no autorizado a ingresar")
        return true
    }

    private suspend fun checkAvailableSpaceForVehicles(tariff: Tariff): Boolean {
        val vehicleFactory = VehicleFactory()
        val tariffPerVehicle: TariffPerVehicle = vehicleFactory.getVehicle(tariff.vehicleType)
        return parkingRepository.getCountVehiclesByType(tariff.vehicleType) < tariffPerVehicle.getMaxQuantityOfVehicles()
    }
}