package com.ceiba.infraestructure.dataAccess.anticorruption

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.exception.InvalidDataException
import com.ceiba.domain.model.Car
import com.ceiba.domain.model.Motorcycle
import com.ceiba.domain.model.Vehicle
import com.ceiba.domain.valueobject.VehicleType
import com.ceiba.infraestructure.dataAccess.entity.TariffEntityRoom

fun Tariff.asTariffEntity(): TariffEntityRoom {
    return TariffEntityRoom(
        vehicle.plate,
        entryDate,
        vehicleType
    ).apply {
        cylinderCapacity = getCylinderCapacity()
    }
}

fun Tariff.getCylinderCapacity(): Int? {
    var cylinderMotorcycle: Int? = null
    if (vehicleType == VehicleType.MOTORCYCLE.type)
        cylinderMotorcycle = (vehicle as Motorcycle).cylinderCapacity
    return cylinderMotorcycle
}


fun List<TariffEntityRoom>.asVehicleList(): List<Tariff> {
    return map {
        val newVehicle: Vehicle = when (it.type) {
            VehicleType.CAR.type -> Car(it.plate)
            VehicleType.MOTORCYCLE.type -> Motorcycle(it.plate, it.cylinderCapacity ?: 0)
            else -> throw InvalidDataException("No se puede definir el tipo de vehiculo")
        }
        Tariff(it.entryDate, newVehicle)
    }
}