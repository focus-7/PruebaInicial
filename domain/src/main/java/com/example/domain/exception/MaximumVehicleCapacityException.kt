package com.example.domain.exception

class MaximumVehicleCapacityException(message: String = "No hay campo disponible para el vehículo.") :
    RuntimeException(message)