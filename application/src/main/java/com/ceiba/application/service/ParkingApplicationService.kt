package com.ceiba.application.service

import com.ceiba.domain.service.SearchVehicleService
import javax.inject.Inject

class ParkingApplicationService @Inject constructor(private var searchVehicleService: SearchVehicleService) {
    fun getVehiclesByPlate(plate: String) = searchVehicleService.getVehiclesByPlate(plate)
}