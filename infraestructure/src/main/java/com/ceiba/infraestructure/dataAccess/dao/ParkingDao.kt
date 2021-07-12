package com.ceiba.infraestructure.dataAccess.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ceiba.infraestructure.dataAccess.entity.TariffEntityRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface ParkingDao {
    @Insert
    fun insertTariff(tariffEntityRoom: TariffEntityRoom): Long

    @Query("DELETE FROM tariff WHERE plate = :plateD")
    fun deleteTariff(plateD: String): Int

    @Query("SELECT * FROM tariff")
    fun getAllVehicles(): Flow<List<TariffEntityRoom>>
}