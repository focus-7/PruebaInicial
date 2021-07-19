package com.ceiba.domain.service

import com.ceiba.domain.interfaceservice.TariffPerVehicle
import com.ceiba.domain.valueobject.Prices

class TariffCarService : TariffPerVehicle {
    companion object {
        const val MAX_CANT_CAR = 20
    }

    override val priceDay: Double
        get() = Prices.CAR.additionalAmount

    override val priceHour: Double
        get() = Prices.CAR.hour

    override val maxQuantity: Int
        get() = MAX_CANT_CAR

    override fun additionalValue(): Double {
        return Prices.CAR.additionalAmount
    }
}