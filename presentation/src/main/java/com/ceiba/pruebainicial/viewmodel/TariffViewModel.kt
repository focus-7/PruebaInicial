package com.ceiba.pruebainicial.viewmodel

import androidx.lifecycle.*
import com.ceiba.application.service.ParkingApplicationService
import com.ceiba.application.service.VehicleApplicationService
import com.ceiba.domain.aggregate.Tariff
import com.ceiba.pruebainicial.fragments.MainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TariffViewModel @Inject constructor(
    private val vehicleApplicationService: VehicleApplicationService,
    private val parkingApplicationService: ParkingApplicationService,
) : ViewModel() {
    private val _screenState: MutableLiveData<MainScreenState> by lazy {
        MutableLiveData<MainScreenState>(MainScreenState.INITIAL)
    }
    val screenState: LiveData<MainScreenState> get() = _screenState

    private val _searchPlateVehicle = MutableStateFlow("")

    fun setSearchPlate(query: String) {
        _searchPlateVehicle.value = query
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    val vehiclesByPlate = _searchPlateVehicle
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            if (query.isBlank()) vehicleApplicationService.getVehicles()
            else parkingApplicationService.getVehiclesByPlate(query).conflate()
        }
        .flowOn(Dispatchers.Default)
        .catch { e: Throwable -> e.printStackTrace() }
        .asLiveData()

    val vehicles: LiveData<List<Tariff>> =
        vehicleApplicationService.getVehicles()
            .flowOn(Dispatchers.IO)
            .catch { e: Throwable -> e.printStackTrace() }
            .asLiveData()

    fun enterVehicle(tariff: Tariff) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                vehicleApplicationService.enterVehicle(tariff)
            }
        }
    }

    fun takeOutVehicle(tariff: Tariff) {
        viewModelScope.launch {
            vehicleApplicationService.takeOutVehicle(tariff)
        }
    }
}