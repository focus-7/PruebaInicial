package com.ceiba.domain.model

import com.ceiba.domain.valueobject.VehicleType

class Motorcycle(plate: String, var cylinderCapacity: Int) :
    Vehicle(plate, VehicleType.MOTORCYCLE.type)