package com.ceiba.infraestructure.dataAccess.anticorruption

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.exception.InvalidDataException
import com.ceiba.domain.model.Car
import com.ceiba.domain.model.Motorcycle
import com.ceiba.domain.model.Vehicle
import com.ceiba.domain.valueobject.VehicleType
import com.ceiba.infraestructure.dataAccess.entity.TariffEntityRoom
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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

fun Flow<List<TariffEntityRoom>>.asVehicleListFlow(): Flow<List<Tariff>> {
    return map {
        it.map { item ->
            val newVehicle: Vehicle = when (item.type) {
                VehicleType.CAR.type -> Car(item.plate)
                VehicleType.MOTORCYCLE.type -> Motorcycle(item.plate, item.cylinderCapacity ?: 0)
                else -> throw InvalidDataException("No se puede definir el tipo de vehiculo")
            }
            Tariff(item.entryDate, newVehicle)
        }
    }
}

