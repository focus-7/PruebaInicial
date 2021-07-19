package com.ceiba.domain.interfaceservice

interface TariffPerVehicle {
    val priceDay: Double
    val priceHour: Double
    val maxQuantity: Int
    fun additionalValue(): Double
}