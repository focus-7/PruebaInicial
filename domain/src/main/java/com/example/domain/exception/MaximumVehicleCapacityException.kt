package com.example.domain.exception

import java.lang.RuntimeException

class MaximumVehicleCapacityException(VEHICLE_CAPACITY_EXCEPTION: String = "No hay campo disponible para el vehículo.") :
    RuntimeException(VEHICLE_CAPACITY_EXCEPTION)