package com.ceiba.domain.service

import com.ceiba.domain.valueobject.Prices

class TariffPerVehicleCarService : TariffPerVehicle {
    override fun getHourPrice(): Double {
        return Prices.CAR.hour
    }

    override fun getDayPrice(): Double {
        return Prices.CAR.day
    }

    override fun getAdditionalValue(): Double {
        return Prices.CAR.additionalAmount
    }
}