package com.example.pruebainicial.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.domain.aggregate.Tariff
import com.example.domain.entity.Vehicle
import com.example.domain.service.TariffService
import com.example.pruebainicial.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class TariffViewModel @Inject constructor(
    private val tariffService: TariffService,
) : ViewModel() {

    fun getVehicles() = liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(tariffService.getVehicles()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun searchVehiclesByPlate(plate: String) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading)
            try {
                emit(Resource.Success(tariffService.getVehiclesByPlate(plate)))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }

    fun enterVehicle(entryDate: Long, vehicle: Vehicle) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading)
            try {
                emit(Resource.Success(tariffService.enterVehicle(entryDate, vehicle)))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }

    fun takeOutVehicle(tariff: Tariff) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading)
            try {
                emit(Resource.Success(tariffService.takeOutVehicle(tariff)))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
}