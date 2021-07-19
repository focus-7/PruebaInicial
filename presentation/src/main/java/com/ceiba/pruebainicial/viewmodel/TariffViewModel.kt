package com.ceiba.pruebainicial.viewmodel

import androidx.lifecycle.*
import com.ceiba.application.service.ParkingApplicationService
import com.ceiba.application.service.VehicleApplicationService
import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.util.Resource
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
        .debounce(SEARCH_DELAY_MILLIS)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            parkingApplicationService.getVehiclesByPlate(query).conflate()
        }
        .flowOn(Dispatchers.Default)
        .catch { e: Throwable -> e.printStackTrace() }
        .asLiveData()

    val vehicles = getVehicles().asLiveData()

    private fun getVehicles(): Flow<Resource<List<Tariff>>> {
        val flow = flow {
            vehicleApplicationService.getVehicles().collect {
                emit(Resource.success(it))
            }
        }
        return flow
            .onStart { emit(Resource.loading(null)) }
            .catch { e ->
                emit(Resource.error(e.message.toString(), null))
            }
            .flowOn(Dispatchers.IO)
    }

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

    fun isEntryValid(
        plate: String,
        cylinderCapacity: String,
        typeMotorcycle: Boolean,
    ): Boolean {
        return when {
            plate.isBlank() -> false
            (typeMotorcycle && cylinderCapacity.isBlank()) -> false
            else -> true
        }
    }

    companion object {
        const val SEARCH_DELAY_MILLIS = 1000L
    }
}