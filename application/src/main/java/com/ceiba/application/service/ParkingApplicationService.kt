package com.ceiba.application.service

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.service.ParkingService
import javax.inject.Inject

class ParkingApplicationService @Inject constructor(private var parkingService: ParkingService) {
    fun getVehiclesByPlate(plate: String): List<Tariff> {
        return parkingService.getVehiclesByPlate(plate)
    }
}