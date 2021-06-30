package com.ceiba.domain.service

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.repository.ParkingRepository
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
}