package com.ceiba.domain.model

import com.ceiba.domain.valueobject.VehicleType
import java.io.Serializable

abstract class Vehicle(var plate: String, val vehicleType: Int) : Serializable

class Motorcycle(plate: String, var cylinderCapacity: Int) :
    Vehicle(plate, VehicleType.MOTORCYCLE.type)

class Car(plate: String) : Vehicle(plate, VehicleType.CAR.type)