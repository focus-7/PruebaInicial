package com.ceiba.domain.service

import com.ceiba.domain.interfaceservice.TariffPerVehicle
import com.ceiba.domain.valueobject.Prices

class TariffMotorcycleService(var cylinderCapacity: Int = 150) : TariffPerVehicle {
    companion object {
        const val MAX_CYLINDER_CAPACITY = 500
        const val MAX_CANT_MOTORCYCLE = 10
    }

    override fun getMaxQuantityOfVehicles(): Int {
        return MAX_CANT_MOTORCYCLE
    }

    override fun getAdditionalValue(): Double {
        return Prices.MOTORCYCLE.additionalAmount.takeIf {
            cylinderCapacity > MAX_CYLINDER_CAPACITY
        } ?: 0.0
    }

    override fun getPriceDay(): Double {
        return Prices.MOTORCYCLE.day
    }

    override fun getPriceHour(): Double {
        return Prices.MOTORCYCLE.hour
    }
}