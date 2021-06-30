package com.ceiba.domain.builder

import com.ceiba.domain.model.Car
import com.ceiba.domain.model.Motorcycle

class VehicleBuilder {
    private var plate = "MSK38E"
    private var cylinderCapacity = 150

    fun withPlate(plate: String): VehicleBuilder {
        this.plate = plate
        return this
    }

    fun withCylinderCapacity(cylinderCapacity: Int): VehicleBuilder {
        this.cylinderCapacity = cylinderCapacity
        return this
    }

    fun buildCar(): Car {
        return Car(plate)
    }

    fun buildMotorcycle(): Motorcycle {
        return Motorcycle(plate, cylinderCapacity)
    }
}