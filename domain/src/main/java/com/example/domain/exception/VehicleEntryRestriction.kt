package com.example.domain.exception

class VehicleEntryRestriction(message: String = "Vehiculo no autorizado a ingresar") :
    RuntimeException(message)