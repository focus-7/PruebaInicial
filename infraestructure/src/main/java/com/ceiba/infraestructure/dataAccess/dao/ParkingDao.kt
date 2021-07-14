package com.ceiba.infraestructure.dataAccess.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ceiba.infraestructure.dataAccess.entity.TariffEntityRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface ParkingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTariff(tariffEntityRoom: TariffEntityRoom)

    @Query("DELETE FROM tariff WHERE plate = :plateD")
    suspend fun deleteTariff(plateD: String)

    @Query("SELECT * FROM tariff")
    fun getAllVehicles(): Flow<List<TariffEntityRoom>>
}