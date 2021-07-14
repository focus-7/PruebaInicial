package com.ceiba.domain.model

import com.ceiba.domain.valueobject.VehicleType
import java.io.Serializable

abstract class Vehicle(val plate: String,  val vehicleType: Int) : Serializable

class Motorcycle(plate: String, val cylinderCapacity: Int) :
    Vehicle(plate, VehicleType.MOTORCYCLE.type)

class Car(plate: String) : Vehicle(plate, VehicleType.CAR.type)