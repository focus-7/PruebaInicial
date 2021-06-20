package com.example.domain.exception

import java.lang.RuntimeException

class VehicleEntryRestriction(VEHICLE_ENTRY_EXCEPTION: String = "Vehiculo no autorizado a ingresar") :
    RuntimeException(VEHICLE_ENTRY_EXCEPTION) {
}