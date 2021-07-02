package com.ceiba.infraestructure.dataAccess.repository

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.repository.ParkingRepository
import com.ceiba.infraestructure.dataAccess.anticorruption.asVehicleList
import com.ceiba.infraestructure.dataAccess.dao.VehicleDao
import javax.inject.Inject

class ParkingRepositoryRoom @Inject constructor(private val vehicleDao: VehicleDao) :
    ParkingRepository {

    override fun getCountVehiclesByType(type: Int): Int {
        return vehicleDao.getCountVehiclesByType(type)
    }

    override fun searchVehicleByPlate(plate: String): List<Tariff> {
        return vehicleDao.searchVehicleByPlate(plate).asVehicleList()
    }
}