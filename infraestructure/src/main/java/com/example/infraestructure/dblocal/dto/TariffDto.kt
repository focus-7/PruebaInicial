package com.example.infraestructure.dblocal.dto

import com.example.domain.aggregate.Tariff
import com.example.domain.entity.Car
import com.example.domain.entity.Motorcycle
import com.example.domain.entity.Vehicle
import com.example.domain.exception.VehicleTypeException
import com.example.infraestructure.dblocal.entity.TariffEntityRoom

fun Tariff.asTariffEntity(): TariffEntityRoom {
    var cc: Int? = null
    val typeVehicle = when (vehicle) {
        is Car -> 1
        is Motorcycle -> {
            cc = (vehicle as Motorcycle).cylinderCapacity
            2
        }
        else -> throw VehicleTypeException()
    }
    return TariffEntityRoom(
        vehicle.plate,
        entryDate,
        typeVehicle
    ).apply {
        cylinderCapacity = cc
    }
}

fun List<TariffEntityRoom>.asVehicleList(): List<Tariff> {
    return map {
        val newVehicle: Vehicle = when (it.type) {
            1 -> Car(it.plate)
            2 -> Motorcycle(it.plate, it.cylinderCapacity ?: 0)
            else -> throw VehicleTypeException()
        }
        Tariff(it.entryDate, newVehicle)
    }
}