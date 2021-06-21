package com.example.infraestructure.dblocal.repository

import com.example.domain.aggregate.Tariff
import com.example.domain.repository.TariffRepository
import com.example.infraestructure.dblocal.dao.TariffDao
import com.example.infraestructure.dblocal.dto.asTariffEntity
import com.example.infraestructure.dblocal.dto.asVehicleList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TariffRepositoryRoom @Inject constructor(private val tariffDao: TariffDao) :
    TariffRepository {
    override fun getQuantityOfVehicles(typeVehicle: Int): Int {
        return tariffDao.getCountVehicle(typeVehicle)
    }

    override fun takeOutVehicle(tariff: Tariff) {
        tariffDao.deleteTariff(tariff.vehicle.plate)
    }

    override fun enterVehicle(tariff: Tariff) {
        tariffDao.insertTariff(tariff.asTariffEntity())
    }

    override fun getVehicles(): List<Tariff> {
        return tariffDao.getVehicles().asVehicleList()
    }

}