package com.ceiba.infraestructure.dataAccess.repository

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.repository.VehicleRepository
import com.ceiba.infraestructure.dataAccess.anticorruption.asTariffEntity
import com.ceiba.infraestructure.dataAccess.anticorruption.asVehicleListFlow
import com.ceiba.infraestructure.dataAccess.dao.ParkingDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VehicleRepositoryRoom @Inject constructor(private val parkingDao: ParkingDao) :
    VehicleRepository {
    override fun takeOutVehicle(tariff: Tariff) {
        parkingDao.deleteTariff(tariff.vehicle.plate)
    }

    override fun enterVehicle(tariff: Tariff) {
        parkingDao.insertTariff(tariff.asTariffEntity())
    }

    override fun getVehicles(): Flow<List<Tariff>> {
        return parkingDao.getAllVehicles().asVehicleListFlow()
    }
}
