package com.ceiba.domain.service

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.repository.ParkingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchVehicleService @Inject constructor(
    private val parkingRepository: ParkingRepository,
) {
    fun getVehiclesByPlate(plate: String): Flow<List<Tariff>> {
        return parkingRepository.searchVehicleByPlate(plate)
    }
}