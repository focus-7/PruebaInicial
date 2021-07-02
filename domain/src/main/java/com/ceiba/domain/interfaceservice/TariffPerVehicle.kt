package com.ceiba.domain.interfaceservice

interface TariffPerVehicle {
    fun getAdditionalValue(): Double
    fun calculateTariffVehicle(hours: Int): Double
    fun getMaxQuantityOfVehicles(): Int
}