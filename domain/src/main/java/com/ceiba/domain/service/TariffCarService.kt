package com.ceiba.domain.service

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.interfaceservice.TariffPerVehicle
import com.ceiba.domain.valueobject.Prices

class TariffCarService : TariffPerVehicle {
    companion object {
        const val MAX_CANT_CAR = 20
    }

    override fun calculateTariffVehicle(hours: Int): Double {
        val amount = when {
            hours < Tariff.MAX_HOUR -> hours * Prices.CAR.hour
            hours in Tariff.MAX_HOUR..Tariff.MAX_HOUR_DAY -> Prices.CAR.day
            else -> {
                val days = hours / Tariff.MAX_HOUR_DAY
                val restOfHours = hours % Tariff.MAX_HOUR_DAY
                Prices.CAR.day * days + Prices.CAR.hour * restOfHours
            }
        }
        return amount + getAdditionalValue()
    }

    override fun getMaxQuantityOfVehicles(): Int {
        return MAX_CANT_CAR
    }

    override fun getAdditionalValue(): Double {
        return Prices.CAR.additionalAmount
    }
}