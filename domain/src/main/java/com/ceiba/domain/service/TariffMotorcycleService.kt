package com.ceiba.domain.service

import com.ceiba.domain.interfaceservice.TariffPerVehicle
import com.ceiba.domain.valueobject.Prices

class TariffMotorcycleService(var cylinderCapacity: Int = 150) : TariffPerVehicle {
    companion object {
        const val MAX_CYLINDER_CAPACITY = 500
        const val MAX_CANT_MOTORCYCLE = 10
    }

    override val priceDay: Double
        get() = Prices.CAR.additionalAmount

    override val priceHour: Double
        get() = Prices.CAR.hour

    override val maxQuantity: Int
        get() = TariffCarService.MAX_CANT_CAR

    override fun additionalValue(): Double {
        return Prices.MOTORCYCLE.additionalAmount.takeIf {
            cylinderCapacity > MAX_CYLINDER_CAPACITY
        } ?: 0.0
    }
}