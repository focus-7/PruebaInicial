package com.ceiba.infraestructure.dataAccess.dao

import androidx.room.Dao
import androidx.room.Query
import com.ceiba.infraestructure.dataAccess.entity.TariffEntityRoom

@Dao
interface VehicleDao {
    @Query("SELECT COUNT(*) FROM tariff WHERE type = :type")
    fun getCountTypeOfVehicle(type: Int): Int

    @Query("SELECT * FROM tariff WHERE plate LIKE :plateS")
    fun searchVehicleByPlate(plateS: String): List<TariffEntityRoom>
}