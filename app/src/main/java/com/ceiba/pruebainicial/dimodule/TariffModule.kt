package com.ceiba.pruebainicial.dimodule

import com.ceiba.domain.repository.ParkingRepository
import com.ceiba.domain.repository.VehicleRepository
import com.ceiba.infraestructure.dataAccess.repository.ParkingRepositoryRoom
import com.ceiba.infraestructure.dataAccess.repository.VehicleRepositoryRoom
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class TariffModule {
    @Binds
    abstract fun bindVehicleRepository(vehicleRepositoryRoom: VehicleRepositoryRoom): VehicleRepository

    @Binds
    abstract fun bindParkingRepository(parkingRepositoryRoom: ParkingRepositoryRoom): ParkingRepository
}