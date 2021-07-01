package com.ceiba.domain.service

import com.ceiba.domain.model.Motorcycle
import com.ceiba.domain.valueobject.Prices

class TariffPerVehicleMotorcycleService(var cylinderCapacity: Int = 150) : TariffPerVehicle {
    override fun getHourPrice(): Double {
        return Prices.MOTORCYCLE.hour
    }

    override fun getDayPrice(): Double {
        return Prices.MOTORCYCLE.day
    }

    override fun getAdditionalValue(): Double {
        return Prices.MOTORCYCLE.additionalAmount.takeIf {
            cylinderCapacity > Motorcycle.MAX_CYLINDER_CAPACITY
        } ?: 0.0
    }
}