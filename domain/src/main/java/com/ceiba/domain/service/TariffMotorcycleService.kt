package com.ceiba.domain.service

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.interfaceservice.TariffPerVehicle
import com.ceiba.domain.valueobject.Prices

class TariffMotorcycleService(var cylinderCapacity: Int = 150) : TariffPerVehicle {
    companion object {
        const val MAX_CYLINDER_CAPACITY = 500
        const val MAX_CANT_MOTORCYCLE = 10
    }

    override fun calculateTariffVehicle(hours: Int): Double {
        val amount = when {
            hours < Tariff.MAX_HOUR -> hours * Prices.MOTORCYCLE.hour
            hours in Tariff.MAX_HOUR..Tariff.MAX_HOUR_DAY -> Prices.MOTORCYCLE.day
            else -> {
                val days = hours / Tariff.MAX_HOUR_DAY
                val restOfHours = hours % Tariff.MAX_HOUR_DAY
                Prices.MOTORCYCLE.day * days + Prices.MOTORCYCLE.hour * restOfHours
            }
        }
        return amount + getAdditionalValue()
    }

    override fun getMaxQuantityOfVehicles(): Int {
        return MAX_CANT_MOTORCYCLE
    }

    override fun getAdditionalValue(): Double {
        return Prices.MOTORCYCLE.additionalAmount.takeIf {
            cylinderCapacity > MAX_CYLINDER_CAPACITY
        } ?: 0.0
    }
}