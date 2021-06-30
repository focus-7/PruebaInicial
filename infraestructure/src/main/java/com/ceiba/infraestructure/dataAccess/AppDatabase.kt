package com.ceiba.infraestructure.dataAccess

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ceiba.infraestructure.dataAccess.dao.ParkingDao
import com.ceiba.infraestructure.dataAccess.dao.VehicleDao
import com.ceiba.infraestructure.dataAccess.entity.TariffEntityRoom

@Database(
    entities = [TariffEntityRoom::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun parkingDao(): ParkingDao
    abstract fun vehicleDao(): VehicleDao
}