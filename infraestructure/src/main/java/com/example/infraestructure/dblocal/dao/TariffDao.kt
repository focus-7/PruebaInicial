package com.example.infraestructure.dblocal.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.infraestructure.dblocal.entity.TariffEntityRoom

@Dao
interface TariffDao {
    @Query("SELECT COUNT(*) FROM tariff WHERE type = :type")
    fun getCountVehicle(type: Int): Int

    @Insert
    fun insertTariff(tariffEntityRoom: TariffEntityRoom): Long

    @Query("DELETE FROM tariff WHERE plate = :plateD")
    fun deleteTariff(plateD: String): Int

    @Query("SELECT * FROM tariff")
    fun getVehicles(): List<TariffEntityRoom>

    @Query("SELECT * FROM tariff WHERE plate LIKE :plateS")
    fun searchVehicleByPlate(plateS: String): List<TariffEntityRoom>
}