package com.ceiba.domain.service

import com.ceiba.domain.interfaceservice.TariffPerVehicle
import com.ceiba.domain.valueobject.Prices

class TariffCarService : TariffPerVehicle {
    companion object {
        const val MAX_CANT_CAR = 20
    }

    override fun getMaxQuantityOfVehicles(): Int {
        return MAX_CANT_CAR
    }

    override fun getAdditionalValue(): Double {
        return Prices.CAR.additionalAmount
    }

    override fun getPriceDay(): Double {
        return Prices.CAR.day
    }

    override fun getPriceHour(): Double {
        return Prices.CAR.hour
    }
}