package com.ceiba.application.service

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.service.SearchVehicleService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ParkingApplicationService @Inject constructor(private var searchVehicleService: SearchVehicleService) {
    fun getVehiclesByPlate(plate: String): Flow<List<Tariff>> {
        return searchVehicleService.getVehiclesByPlate(plate)
    }
}