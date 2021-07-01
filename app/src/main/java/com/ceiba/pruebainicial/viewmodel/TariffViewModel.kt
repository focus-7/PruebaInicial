package com.ceiba.pruebainicial.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.model.Vehicle
import com.ceiba.domain.service.ParkingService
import com.ceiba.domain.service.VehicleService
import com.ceiba.pruebainicial.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class TariffViewModel @Inject constructor(
    private val vehicleService: VehicleService,
    private val parkingService: ParkingService
) : ViewModel() {

     fun getVehicles() = liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(vehicleService.getVehicles()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun searchVehiclesByPlate(plate: String) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading)
            try {
                emit(Resource.Success(parkingService.getVehiclesByPlate(plate)))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }

    fun enterVehicle(tariff: Tariff) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading)
            try {
                emit(Resource.Success(vehicleService.enterVehicle(tariff)))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }



    fun takeOutVehicle(tariff: Tariff) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading)
            try {
                emit(Resource.Success(vehicleService.takeOutVehicle(tariff)))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
}