package com.example.domain.exception

import java.lang.RuntimeException

class VehicleTypeException(ERROR_VEHICLE_TYPE: String = "No se puede definir el tipo de vehiculo") :
    RuntimeException(ERROR_VEHICLE_TYPE)
