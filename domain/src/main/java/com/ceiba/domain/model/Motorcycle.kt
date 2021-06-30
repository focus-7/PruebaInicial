package com.ceiba.domain.model

import com.ceiba.domain.valueobject.Prices
import com.ceiba.domain.valueobject.VehicleType

class Motorcycle(
    plate: String, var cylinderCapacity: Int,
) : Vehicle(plate, VehicleType.MOTORCYCLE) {
    companion object {
        const val MAX_CYLINDER_CAPACITY = 500
    }

    private fun cylinderCapacityGreaterThan500(): Boolean {
        return cylinderCapacity > MAX_CYLINDER_CAPACITY
    }

    fun getAdditionalAmountMotorcycle(): Double {
        if (cylinderCapacityGreaterThan500())
            return Prices.MOTORCYCLE.additionalAmount
        return 0.0
    }
}