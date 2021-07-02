package com.ceiba.pruebainicial.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.ceiba.application.service.ParkingApplicationService
import com.ceiba.application.service.VehicleApplicationService
import com.ceiba.domain.aggregate.Tariff
import com.ceiba.pruebainicial.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class TariffViewModel @Inject constructor(
    private val vehicleApplicationService: VehicleApplicationService,
    private val parkingApplicationService: ParkingApplicationService,
) : ViewModel() {

    fun getVehicles() = liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(vehicleApplicationService.getVehicles()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun searchVehiclesByPlate(plate: String) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading)
            try {
                emit(Resource.Success(parkingApplicationService.getVehiclesByPlate(plate)))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }

    fun enterVehicle(tariff: Tariff) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading)
            try {
                emit(Resource.Success(vehicleApplicationService.enterVehicle(tariff)))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }

    fun takeOutVehicle(tariff: Tariff) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading)
            try {
                emit(Resource.Success(vehicleApplicationService.takeOutVehicle(tariff)))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
}