package com.example.infraestructure.dblocal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.infraestructure.dblocal.dao.TariffDao
import com.example.infraestructure.dblocal.entity.TariffEntityRoom

@Database(
    entities = [TariffEntityRoom::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tariffDao(): TariffDao
}