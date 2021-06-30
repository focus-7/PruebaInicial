package com.ceiba.domain.util

import com.ceiba.domain.model.Vehicle

class VehicleGeneric<T>(private val type: Class<T>) {
    fun getTypeOfVehicle(vehicle: Vehicle?): T? {
        var objAsType: T? = null
        if (type.isInstance(vehicle))
            objAsType = type.cast(vehicle)
        return objAsType
    }
}