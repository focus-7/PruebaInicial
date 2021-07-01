package com.ceiba.infraestructure.dataAccess.dao

import androidx.room.Dao
import androidx.room.Query
import com.ceiba.infraestructure.dataAccess.entity.TariffEntityRoom

@Dao
interface VehicleDao {
    @Query("SELECT COUNT(*) FROM tariff WHERE type = 1")
    fun getCountCars(): Int

    @Query("SELECT COUNT(*) FROM tariff WHERE type = 2")
    fun getCountMotorcycles(): Int

    @Query("SELECT * FROM tariff WHERE plate LIKE :plateS")
    fun searchVehicleByPlate(plateS: String): List<TariffEntityRoom>
}