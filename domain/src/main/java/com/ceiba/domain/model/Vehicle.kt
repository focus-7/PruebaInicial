package com.ceiba.domain.model

import com.ceiba.domain.valueobject.VehicleType
import java.io.Serializable


abstract class Vehicle(var plate: String, val vehicleType: VehicleType) : Serializable {
    companion object {
        const val PLATE_INITIAL_LETTER = 'A'
    }

    fun plateInitWithA(): Boolean {
        return plate[0] == PLATE_INITIAL_LETTER
    }
}