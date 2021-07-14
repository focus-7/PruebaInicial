package com.ceiba.infraestructure.dataAccess.repository

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.repository.VehicleRepository
import com.ceiba.infraestructure.dataAccess.dao.ParkingDao
import com.ceiba.infraestructure.dataAccess.anticorruption.asTariffEntity
import com.ceiba.infraestructure.dataAccess.anticorruption.asVehicleListFlow
import javax.inject.Inject

class VehicleRepositoryRoom @Inject constructor(private val parkingDao: ParkingDao) :
    VehicleRepository {
    override suspend fun takeOutVehicle(tariff: Tariff) {
        parkingDao.deleteTariff(tariff.vehicle.plate)
    }

    override suspend fun enterVehicle(tariff: Tariff) {
        parkingDao.insertTariff(tariff.asTariffEntity())
    }

    override fun getVehicles() = parkingDao.getAllVehicles().asVehicleListFlow()
}