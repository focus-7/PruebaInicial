package com.ceiba.infraestructure.dataAccess.repository

import com.ceiba.domain.repository.ParkingRepository
import com.ceiba.infraestructure.dataAccess.anticorruption.asVehicleListFlow
import com.ceiba.infraestructure.dataAccess.dao.VehicleDao
import javax.inject.Inject

class ParkingRepositoryRoom @Inject constructor(private val vehicleDao: VehicleDao) :
    ParkingRepository {

    override suspend fun getCountVehiclesByType(type: Int): Int {
        return vehicleDao.getCountVehiclesByType(type)
    }

    override fun searchVehicleByPlate(plate: String) =
        vehicleDao.searchVehicleByPlate(plate).asVehicleListFlow()
}