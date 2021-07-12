package com.ceiba.pruebainicial.dimodule

import android.content.Context
import androidx.room.Room
import com.ceiba.infraestructure.dataAccess.AppDatabase
import com.ceiba.infraestructure.dataAccess.dao.ParkingDao
import com.ceiba.infraestructure.dataAccess.dao.VehicleDao
import com.ceiba.infraestructure.dataAccess.util.AppConstants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideParkingDao(db: AppDatabase): ParkingDao = db.parkingDao()

    @Singleton
    @Provides
    fun provideVehicleDao(db: AppDatabase): VehicleDao = db.vehicleDao()
}