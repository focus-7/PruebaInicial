package com.ceiba.domain.interfaceservice

interface TariffPerVehicle {
    fun getAdditionalValue(): Double
    fun getPriceDay(): Double
    fun getPriceHour(): Double
    fun getMaxQuantityOfVehicles(): Int
}